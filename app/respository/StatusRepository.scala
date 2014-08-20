package respository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PrimaryKey
import conf.DataConnection
import domain.Status

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/11.
 */
class StatusRepository extends CassandraTable[StatusRepository, Status] {

  object subjectId extends StringColumn(this) with PartitionKey[String]

  object status extends StringColumn(this)

  override def fromRow(row: Row): Status = {
    Status(subjectId(row),
      status(row))
  }
}

object StatusRepository extends StatusRepository with DataConnection {
  override lazy val tableName = "status"

  def save(status: Status): Future[ResultSet] = {
    insert
      .value(_.subjectId, status.subjectId)
      .value(_.status, status.status)
      .future()
  }
  def getStatusById(subjectId: String): Future[Option[Status]] = {
    select.where(_.subjectId eqs subjectId).one();
  }

  def updateStatus(subjectId:String, status:String):Future[ResultSet] = {
    update.where(_.subjectId eqs subjectId)
      .modify(_.status setTo status )
      .future()
  }
}
