package respository

import java.util.UUID

import com.datastax.driver.core.{ResultSet, Row}
import com.newzly.phantom.CassandraTable
import com.newzly.phantom.Implicits._
import com.newzly.phantom.column.{DateTimeColumn, TimeUUIDColumn}
import com.newzly.phantom.iteratee.Iteratee
import com.newzly.phantom.keys.{PartitionKey, PrimaryKey}
import conf.DataConnection
import domain.Question
import org.joda.time.DateTime

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/16.
 */
class QuestionRespository extends CassandraTable[QuestionRespository, Question] {

  object id extends TimeUUIDColumn(this) with PartitionKey[UUID]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime]

  object title extends StringColumn(this)

  object detail extends StringColumn(this)

  object authorId extends StringColumn(this)


  override def fromRow(row: Row): Question = {
    Question(id(row), date(row), title(row), detail(row), authorId(row))
  }
}

object QuestionRespository extends QuestionRespository with DataConnection {
  override lazy val tableName = "questions"

  def save(question: Question): Future[ResultSet] = {
    insert
      .value(_.id, question.id)
      .value(_.date, question.date)
      .value(_.title, question.title)
      .value(_.detail, question.detail)
      .value(_.authorId, question.authourId)
      .future()
  }

  def geAllQuestions: Future[Seq[Question]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getRoleById(id: UUID): Future[Option[Question]] = {
    select.where(_.id eqs id).one()
  }

  def deleteRoleById(id: UUID): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }

}
