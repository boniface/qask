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
import domain.Feeds

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/12.
 */
class FeedsRespository extends CassandraTable[FeedsRespository, Feeds] {

  object id extends StringColumn(this) with PartitionKey[String]
  object zone extends StringColumn(this) with PrimaryKey[String]

  object feedLink extends StringColumn(this)

  object feedType extends StringColumn(this)

  object feedSite extends StringColumn(this)

  object siteLogo extends StringColumn(this)


  override def fromRow(row: Row): Feeds = {
    Feeds(id(row),
      zone(row),
      feedLink(row),
      feedType(row),
      feedSite(row),
      siteLogo(row))
  }
}

object FeedsRespository extends FeedsRespository with DataConnection {
  override lazy val tableName = "feeds"

  def save(feed: Feeds): Future[ResultSet] = {
    insert
      .value(_.id, feed.id)
      .value(_.feedLink, feed.feedLink)
      .value(_.feedType, feed.feedType)
      .value(_.feedSite, feed.feedSite)
      .value(_.siteLogo, feed.siteLogo)
      .value(_.zone, feed.zone)
      .future()
  }

  def getFeedById(feedId: String): Future[Option[Feeds]] = {
    select.where(_.id eqs feedId).one();
  }

  def getAllFeeds: Future[Seq[Feeds]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteFeedById(feedId: String): Future[ResultSet] = {
    delete.where(_.id eqs feedId).future();
  }

  def updateFeed(feedId: String, feed: Feeds) = {
    update.where(_.id eqs feedId).modify(_.feedLink setTo feed.feedLink)
      .and(_.feedType setTo feed.feedType)
      .and(_.feedSite setTo feed.feedSite)
      .and(_.siteLogo setTo feed.siteLogo)
      .future()
  }

}
