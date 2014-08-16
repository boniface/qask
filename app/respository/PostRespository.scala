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
import domain.Post
import org.joda.time.DateTime

import scala.concurrent.Future


class PostRespository extends CassandraTable[PostRespository, Post] {

  object linkhash extends StringColumn(this) with PartitionKey[String]

  object zone extends StringColumn(this) with PrimaryKey[String]

  object domain extends StringColumn(this) with PrimaryKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime]

  object title extends StringColumn(this)

  object article extends StringColumn(this)

  object metakeywords extends StringColumn(this)

  object metaDescription extends StringColumn(this)

  object link extends StringColumn(this)

  override def fromRow(row: Row): Post = {
    Post(
      linkhash(row),
      zone(row),
      domain(row),
      date(row),
      title(row),
      article(row),
      metakeywords(row),
      metaDescription(row),
      link(row)
    )
  }
}

object PostRespository extends PostRespository with DataConnection {
  override lazy val tableName = "posts"

  def save(post: Post): Future[ResultSet] = {
    insert
      .value(_.linkhash, post.linkhash)
      .value(_.domain, post.domain)
      .value(_.date, post.date)
      .value(_.title, post.title)
      .value(_.article, post.article)
      .value(_.metakeywords, post.metakeywords)
      .value(_.metaDescription, post.metaDescription)
      .value(_.link, post.link)
      .value(_.zone, post.zone)
      .future()
  }

  def getPostById(linkhash: String): Future[Option[Post]] = {
    select.where(_.linkhash eqs linkhash).one();
  }

  def getAllPosts: Future[Seq[Post]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getPostsBySite(linkhash: String, domain: String): Future[Seq[Post]] = {
    select.where(_.linkhash eqs linkhash).and(_.domain eqs domain).fetchEnumerator() run Iteratee.collect()
  }

}