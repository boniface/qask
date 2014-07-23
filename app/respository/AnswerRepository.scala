package respository

import java.util.UUID

import com.datastax.driver.core.{ResultSet, Row}
import com.newzly.phantom.CassandraTable
import com.newzly.phantom.Implicits._
import com.newzly.phantom.column.{DateTimeColumn, TimeUUIDColumn}
import com.newzly.phantom.iteratee.Iteratee
import com.newzly.phantom.keys.{PartitionKey, PrimaryKey}
import conf.DataConnection
import domain.Answer

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/09.
 */
sealed class AnswerRepository extends CassandraTable[AnswerRepository, Answer] {

  object questionId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object date extends DateTimeColumn(this)

  object answer extends StringColumn(this)

  object email extends StringColumn(this)

  object screenName extends StringColumn(this)

  object ipaddress extends StringColumn(this)

  override def fromRow(row: Row): Answer = {
    Answer(questionId(row), id(row), date(row), answer(row), email(row), screenName(row), ipaddress(row))
  }
}

object AnswerRepository extends AnswerRepository with DataConnection {
  override lazy val tableName = "answers"

  def save(answer: Answer): Future[ResultSet] = {
    insert
      .value(_.questionId, answer.questionId)
      .value(_.id, answer.id)
      .value(_.date, answer.date)
      .value(_.email, answer.email)
      .value(_.screenName, answer.screenName)
      .value(_.answer, answer.answer)
      .value(_.ipaddress, answer.ipaddress)
      .future()
  }

  def getAnswersByQuestionId(questionId: String): Future[Seq[Answer]] = {
    select.where(_.questionId eqs questionId).fetchEnumerator() run Iteratee.collect()
  }

}
