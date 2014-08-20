package respository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PrimaryKey
import conf.DataConnection
import domain.Response
import net.liftweb.json._

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/09.
 */
sealed class ResponseRepository extends CassandraTable[ResponseRepository, Response] {
  implicit val formats = DefaultFormats

  object subjectId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object zone extends StringColumn(this) with PrimaryKey[String]

  object date extends DateTimeColumn(this)

  object response extends StringColumn(this)

  object email extends StringColumn(this)

  object screenName extends StringColumn(this)

  object ipaddress extends StringColumn(this)

  //  object comments extends JsonSetColumn[ResponseRepository, Response, Comment](this) {
  //    override def fromJson(obj: String): Comment = {
  //      JsonParser.parse(obj).extract[Comment]
  //    }
  //
  //    override def toJson(obj: Comment): String = {
  //      pretty(render(Extraction.decompose(obj)))
  //    }
  //  }


  override def fromRow(row: Row): Response = {

    Response(subjectId(row),
      id(row),
      zone(row),
      date(row),
      response(row),
      email(row),
      screenName(row),
      ipaddress(row))
  }
}

object ResponseRepository extends ResponseRepository with DataConnection {
  override lazy val tableName = "answers"

  def save(response: Response): Future[ResultSet] = {
    insert
      .value(_.subjectId, response.subjectId)
      .value(_.id, response.id)
      .value(_.date, response.date)
      .value(_.email, response.email)
      .value(_.zone, response.zone)
      .value(_.screenName, response.screenName)
      .value(_.response, response.response)
      .value(_.ipaddress, response.ipaddress)
      .future()
  }

  def getResponseBySubjectId(subjectId: String, zone:String): Future[Seq[Response]] = {
    select.where(_.subjectId eqs subjectId).and(_.zone eqs zone).fetchEnumerator() run Iteratee.collect()
  }

  def updateResponse(subjectId: String, response:String):Future[ResultSet] = {
    update.where(_.id eqs subjectId)
      .modify(_.response setTo response )
      .future()
  }
}
