package respository

import java.util.UUID

import com.datastax.driver.core.{ResultSet, Row}
import com.newzly.phantom.CassandraTable
import com.newzly.phantom.Implicits._
import com.newzly.phantom.iteratee.Iteratee
import com.newzly.phantom.keys.PartitionKey
import conf.DataConnection
import domain.Role

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/07.
 */
sealed class RoleRepository extends CassandraTable[RoleRepository, Role] {

  object id extends UUIDColumn(this) with PartitionKey[UUID]

  object rolename extends StringColumn(this)

  object description extends StringColumn(this)

  override def fromRow(row: Row): Role = {
    Role(
      id(row),
      rolename(row),
      description(row)
    )
  }
}

object RoleRepository extends RoleRepository with DataConnection {
  override lazy val tableName = "roles"

  def save(role: Role): Future[ResultSet] = {
    insert
      .value(_.id, role.id)
      .value(_.description, role.description)
      .value(_.rolename, role.rolename)
      .future()
  }

  def getRoleById(id: UUID): Future[Option[Role]] = {
    select.where(_.id eqs id).one()
  }

  def getAllRoles: Future[Seq[Role]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def updateRoleDescription(id: UUID, description: String): Future[ResultSet] = {
    update.where(_.id eqs id).modify(_.description setTo (description)).future()
  }

  def updateRoleName(id: UUID, rolename: String): Future[ResultSet] = {
    update.where(_.id eqs id).modify(_.rolename setTo (rolename)).future()
  }

  def deleteRoleById(id: UUID): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }

}


