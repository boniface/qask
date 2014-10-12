package respository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import conf.DataConnection
import domain.Account


import scala.concurrent.Future

/**
 * Created by hashcode on 2014/08/25.
 */
class AccountRepository extends CassandraTable[AccountRepository, Account] {

  object email extends StringColumn(this) with PartitionKey[String]

  object screenName extends StringColumn(this) with PrimaryKey[String]

  object authcode extends StringColumn(this)

  object social extends MapColumn[AccountRepository, Account, String, String](this)

  override def fromRow(row: Row): Account = {
    Account(
      email(row),
      screenName(row),
      authcode(row),
      social(row))
  }
}

object AccountRepository extends AccountRepository with DataConnection {
  override lazy val tableName = "accounts"

  def save(user: Account): Future[ResultSet] = {
    insert
      .value(_.email, user.email)
      .value(_.screenName, user.screenName)
      .value(_.authcode, user.authcode)
      .value(_.social, user.social)
      .future()

  }

  def getUserById(email: String): Future[Option[Account]] = {
    select.where(_.email eqs email).one()
  }

  def getUserByScreen(email: String, screenName: String): Future[Option[Account]] = {
    select.where(_.email eqs email).and(_.screenName eqs screenName).one()
  }

  def getUsers: Future[Seq[Account]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def addSocial(email:String,social:Map[String,String]): Future[ResultSet]={
    update.where(_.email eqs email).modify(_.social putAll social).future()
  }

  def deleteUser(email: String): Future[ResultSet] = {
    delete.where(_.email eqs email).future();
  }




}
