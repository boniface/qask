package respository

import java.util.UUID

import com.datastax.driver.core.{ResultSet, Row}
import com.newzly.phantom.CassandraTable
import com.newzly.phantom.Implicits._
import com.newzly.phantom.column.{DateTimeColumn, TimeUUIDColumn}
import com.newzly.phantom.iteratee.Iteratee
import com.newzly.phantom.keys.{PartitionKey, PrimaryKey}
import conf.DataConnection
import domain.ReplyResponse

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/09.
 */
sealed class ResponseReplyRepository extends CassandraTable[ResponseReplyRepository, ReplyResponse] {

  object responseId extends StringColumn(this) with PartitionKey[String]

  object id extends TimeUUIDColumn(this) with PrimaryKey[UUID]

  object date extends DateTimeColumn(this)

  object comment extends StringColumn(this)

  object userId extends StringColumn(this)

  object seo extends StringColumn(this)

  object ipaddress extends StringColumn(this)

  override def fromRow(row: Row): ReplyResponse = {
    ReplyResponse(responseId(row), id(row), date(row), comment(row), userId(row), seo(row), ipaddress(row))
  }
}

object ResponseReplyRepository extends ResponseReplyRepository with DataConnection {
  override lazy val tableName = "replies"

  def save(reply: ReplyResponse): Future[ResultSet] = {
    insert
      .value(_.responseId, reply.responseId)
      .value(_.id, reply.id)
      .value(_.date, reply.date)
      .value(_.userId, reply.userId)
      .value(_.seo, reply.seo)
      .value(_.comment, reply.commnent)
      .value(_.ipaddress, reply.ipaddress)
      .future()
  }

  def getReplyByResponseId(respinseId: String): Future[Seq[ReplyResponse]] = {
    select.where(_.responseId eqs respinseId).fetchEnumerator() run Iteratee.collect()
  }

}
