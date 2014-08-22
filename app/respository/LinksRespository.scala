/*
 * Copyright (c) 2014 Hashcode (Z) Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package respository

import java.util.Date

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import conf.DataConnection
import domain.Link
import org.joda.time.DateTime

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/12.
 */
class LinksRespository extends CassandraTable[LinksRespository, Link] {

  object zone extends StringColumn(this) with PartitionKey[String]
  object linkhash extends StringColumn(this) with PrimaryKey[String]
  object datepublished extends DateColumn(this) with PrimaryKey[Date]


  object url extends StringColumn(this)

  object site extends StringColumn(this)

  override def fromRow(row: Row): Link = {
    Link(zone(row),linkhash(row), datepublished(row),url(row), site(row))
  }
}

object LinksRespository extends LinksRespository with DataConnection {
  override lazy val tableName = "links"

  def save(link: Link): Future[ResultSet] = {
    insert
      .value(_.linkhash, link.linkhash)
      .value(_.site, link.site)
      .value(_.url, link.url)
      .value(_.zone, link.zone)
      .value(_.datepublished, link.datePublished)
      .future()
  }

  def getLinkById(linkhash: String): Future[Option[Link]] = {
    select.where(_.linkhash eqs linkhash).one();
  }

  def getAllLinks: Future[Seq[Link]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getLinksByDate(): Future[Seq[Link]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}
