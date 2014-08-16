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
import conf.DataConnection
import domain.Site

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/12.
 */
class SiteRepository extends CassandraTable[SiteRepository, Site] {

  object id extends StringColumn(this) with PartitionKey[String]
  object zone extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object url extends StringColumn(this)

  object description extends StringColumn(this)

  override def fromRow(row: Row): Site = {
    Site(id(row),zone(row), name(row), url(row), description(row))
  }
}

object SiteRepository extends SiteRepository with DataConnection {
  override lazy val tableName = "site"

  def save(site: Site): Future[ResultSet] = {
    insert
      .value(_.id, site.id)
      .value(_.name, site.name)
      .value(_.url, site.url)
      .value(_.description, site.description)
      .value(_.zone, site.zone)
      .future()
  }

  def getSiteById(siteId: String): Future[Option[Site]] = {
    select.where(_.id eqs siteId).one();
  }

  def deleteSiteById(siteId: String): Future[ResultSet] = {
    delete.where(_.id eqs siteId).future();
  }

  def updateSite(siteId: String, site: Site) = {
    update.where(_.id eqs siteId)
      .modify(_.name setTo site.name)
      .and(_.url setTo site.url)
      .and(_.description setTo site.description)
      .future()
  }
}
