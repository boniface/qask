package respository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PrimaryKey
import conf.DataConnection
import domain.PostStatus

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/11.
 */
class PostStatusRepository extends CassandraTable[PostStatusRepository, PostStatus] {

  object postId extends StringColumn(this) with PartitionKey[String]

  object status extends StringColumn(this)



  override def fromRow(row: Row): PostStatus = {
    PostStatus(postId(row),
      status(row))
  }
}

object PostStatusRepository extends PostStatusRepository with DataConnection {
  override lazy val tableName = "poststatus"

  def save(status: PostStatus): Future[ResultSet] = {
    insert
      .value(_.postId, status.postId)
      .value(_.status, status.status)
      .future()
  }

  def getStatusById(postId: String): Future[Option[PostStatus]] = {
    select.where(_.postId eqs postId).one();

  }
}
