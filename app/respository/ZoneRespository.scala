package respository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import conf.DataConnection
import domain.{Zone}

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/08/17.
 */
class ZoneRespository extends CassandraTable[ZoneRespository, Zone] {

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object code extends StringColumn(this)

  object status extends StringColumn(this)

  override def fromRow(row: Row): Zone = {
    Zone(
      id(row),
      name(row),
      code(row),
      status(row)
    )
  }
}

object ZoneRespository extends ZoneRespository with DataConnection {
  override lazy val tableName = "zones"

  def save(zone: Zone): Future[ResultSet] = {
    insert
      .value(_.id, zone.id)
      .value(_.name, zone.name)
      .value(_.code, zone.code)
      .value(_.status, zone.status)
      .future()
  }

  def getZoneById(id: String): Future[Option[Zone]] = {
    select.where(_.id eqs id).one()
  }

  def getAllZones: Future[Seq[Zone]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def updateZone(zone:Zone): Future[ResultSet] = {
    update.where(_.id eqs zone.id)
      .modify(_.name setTo zone.name )
      .and(_.code setTo zone.code)
      .and(_.status setTo zone.status)
      .future()
  }

  def deleteZoneById(id: String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }

}


