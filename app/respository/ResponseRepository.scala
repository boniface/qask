package respository

import java.util.UUID

import com.datastax.driver.core.{ResultSet, Row}
import com.newzly.phantom.CassandraTable
import com.newzly.phantom.Implicits._
import com.newzly.phantom.column.{DateTimeColumn, TimeUUIDColumn}
import com.newzly.phantom.iteratee.Iteratee
import com.newzly.phantom.keys.{PartitionKey, PrimaryKey}
import conf.DataConnection
import domain.Response

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/09.
 */
sealed class ResponseRepository extends CassandraTable[ResponseRepository, Response] {

  object topicId extends StringColumn(this) with PartitionKey[String]

  object id extends TimeUUIDColumn(this) with PrimaryKey[UUID]

  object date extends DateTimeColumn(this)

  object comment extends StringColumn(this)

  object userId extends StringColumn(this)

  object seo extends StringColumn(this)

  object ipaddress extends StringColumn(this)

  override def fromRow(row: Row): Response = {
    Response(topicId(row), id(row), date(row), comment(row), userId(row), seo(row), ipaddress(row))
  }
}

object ResponseRepository extends ResponseRepository with DataConnection {
  override lazy val tableName = "replies"

  def save(response: Response): Future[ResultSet] = {
    insert
      .value(_.topicId, response.topicId)
      .value(_.id, response.id)
      .value(_.date, response.date)
      .value(_.userId, response.userId)
      .value(_.seo, response.seo)
      .value(_.comment, response.commnent)
      .value(_.ipaddress, response.ipaddress)
      .future()
  }

  def getReplyByResponseId(topicId: String): Future[Seq[Response]] = {
    select.where(_.topicId eqs topicId).fetchEnumerator() run Iteratee.collect()
  }

}
