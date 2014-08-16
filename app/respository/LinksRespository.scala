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

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import conf.DataConnection
import domain.Links
import org.joda.time.DateTime

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/12.
 */
class LinksRespository extends CassandraTable[LinksRespository, Links] {

  object linkhash extends StringColumn(this) with PartitionKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime]
  object zone extends StringColumn(this) with PrimaryKey[String]

  object url extends StringColumn(this)

  object site extends StringColumn(this)

  override def fromRow(row: Row): Links = {
    Links(linkhash(row), site(row),zone(row), url(row), date(row))
  }
}

object LinksRespository extends LinksRespository with DataConnection {
  override lazy val tableName = "links"

  def save(link: Links): Future[ResultSet] = {
    insert
      .value(_.linkhash, link.linkhash)
      .value(_.site, link.site)
      .value(_.url, link.url)
      .value(_.date, link.date)
      .future()
  }

  def getLinkById(linkhash: String): Future[Option[Links]] = {
    select.where(_.linkhash eqs linkhash).one();
  }

  def getAllLinks: Future[Seq[Links]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getLinksByDate(): Future[Seq[Links]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}
