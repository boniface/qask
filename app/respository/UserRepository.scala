package respository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import conf.DataConnection
import domain.User


import scala.concurrent.Future

/**
 * Created by hashcode on 2014/08/25.
 */
class UserRepository extends CassandraTable[UserRepository, User] {

  object email extends StringColumn(this) with PartitionKey[String]

  object screenName extends StringColumn(this) with PrimaryKey[String]

  object authcode extends StringColumn(this)

  object social extends MapColumn[UserRepository, User, String, String](this)

  override def fromRow(row: Row): User = {
    User(
      email(row),
      screenName(row),
      authcode(row),
      social(row))
  }
}

object UserRepository extends UserRepository with DataConnection {
  override lazy val tableName = "feeds"

  def save(user: User): Future[ResultSet] = {
    insert
      .value(_.email, user.email)
      .value(_.screenName, user.screenName)
      .value(_.authcode, user.authcode)
      .value(_.social, user.social)
      .future()

  }

  def getUserById(email: String): Future[Option[User]] = {
    select.where(_.email eqs email).one()
  }

  def getUserByScreen(email: String, screenName: String): Future[Option[User]] = {
    select.where(_.email eqs email).and(_.screenName eqs screenName).one()
  }

  def getUsers: Future[Seq[User]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def addSocial(email:String,social:Map[String,String]): Future[ResultSet]={
    update.where(_.email eqs email).modify(_.social putAll social).future()
  }

  def deleteUser(email: String): Future[ResultSet] = {
    delete.where(_.email eqs email).future();
  }




}
