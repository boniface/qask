package respository

import java.util.{Date, UUID}

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PrimaryKey
import conf.DataConnection
import domain.{Role, Question, Comment}
import org.joda.time.DateTime
import respository.RoleRepository._

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/16.
 */
class QuestionRespository extends CassandraTable[QuestionRespository, Question] {

  object id extends StringColumn(this) with PrimaryKey[String]

  object zone extends StringColumn(this) with PartitionKey[String]

  object date extends DateColumn(this) with PrimaryKey[Date]

  object title extends StringColumn(this)

  object detail extends StringColumn(this)

  object email extends StringColumn(this)

  object screenName extends StringColumn(this)



  override def fromRow(row: Row): Question = {
    Question(id(row),zone(row), date(row), title(row), detail(row), email(row),screenName(row))
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
      .value(_.email, question.email)
      .value(_.zone, question.zone)
      .value(_.screenName, question.screenNane)
      .future()
  }

  def geQuestionsByZone(zone:String): Future[Seq[Question]] = {
    select.where(_.zone eqs zone).fetchEnumerator() run Iteratee.collect()
  }

  def getQuestionById(zone:String,id: String): Future[Option[Question]] = {
    select.where(_.zone eqs zone).and(_.id eqs id).one()
  }



}
