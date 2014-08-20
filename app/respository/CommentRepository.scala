package respository

import java.util.UUID

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PrimaryKey
import conf.DataConnection
import domain.Comment

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/09.
 */
sealed class CommentRepository extends CassandraTable[CommentRepository, Comment] {

  object responseId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]
  
  object zone extends StringColumn(this) with PrimaryKey[String]

  object date extends DateTimeColumn(this)

  object comment extends StringColumn(this)

  object email extends StringColumn(this)

  object screenName extends StringColumn(this)

  object ipaddress extends StringColumn(this)

  override def fromRow(row: Row): Comment = {
    Comment(responseId(row), id(row),zone(row), date(row), comment(row), email(row), screenName(row), ipaddress(row))
  }
}

object CommentRepository extends CommentRepository with DataConnection {
  override lazy val tableName = "comments"

  def save(comment: Comment): Future[ResultSet] = {
    insert
      .value(_.responseId, comment.responseId)
      .value(_.zone, comment.zone)
      .value(_.id, comment.id)
      .value(_.date, comment.date)
      .value(_.screenName, comment.screenName)
      .value(_.email, comment.email)
      .value(_.comment, comment.comment)
      .value(_.ipaddress, comment.ipaddress)
      .future()
  }

  def getCommentsByResponseId(responseId: String, zone:String): Future[Seq[Comment]] = {
    select.where(_.responseId eqs responseId).and(_.zone eqs zone).fetchEnumerator() run Iteratee.collect()
  }

}
