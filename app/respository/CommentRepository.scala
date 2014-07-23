package respository

import java.util.UUID

import com.datastax.driver.core.{ResultSet, Row}
import com.newzly.phantom.CassandraTable
import com.newzly.phantom.Implicits._
import com.newzly.phantom.column.{DateTimeColumn, TimeUUIDColumn}
import com.newzly.phantom.iteratee.Iteratee
import com.newzly.phantom.keys.{PartitionKey, PrimaryKey}
import conf.DataConnection
import domain.Comment

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/09.
 */
sealed class CommentRepository extends CassandraTable[CommentRepository, Comment] {

  object answerId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object date extends DateTimeColumn(this)

  object comment extends StringColumn(this)

  object email extends StringColumn(this)

  object screenName extends StringColumn(this)

  object ipaddress extends StringColumn(this)

  override def fromRow(row: Row): Comment = {
    Comment(answerId(row), id(row), date(row), comment(row), email(row), screenName(row), ipaddress(row))
  }
}

object CommentRepository extends CommentRepository with DataConnection {
  override lazy val tableName = "comments"

  def save(comment: Comment): Future[ResultSet] = {
    insert
      .value(_.answerId, comment.answerId)
      .value(_.id, comment.id)
      .value(_.date, comment.date)
      .value(_.screenName, comment.screenName)
      .value(_.email, comment.email)
      .value(_.comment, comment.comment)
      .value(_.ipaddress, comment.ipaddress)
      .future()
  }

  def getCommentsByAnswerId(answerId: String): Future[Seq[Comment]] = {
    select.where(_.answerId eqs answerId).fetchEnumerator() run Iteratee.collect()
  }

}
