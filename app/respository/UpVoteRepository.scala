package respository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PrimaryKey
import conf.DataConnection
import domain.UpVote

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/08.
 */
class UpVoteRepository extends CassandraTable[UpVoteRepository, UpVote] {

  object subjectId extends StringColumn(this) with PartitionKey[String]

  object voterId extends StringColumn(this) with PrimaryKey[String]

  object vote extends StringColumn(this)

  override def fromRow(row: Row): UpVote = {
    UpVote(subjectId(row), voterId(row), vote(row))
  }
}

object UpVoteRepository extends UpVoteRepository with DataConnection {
  override lazy val tableName = "upvotes"

  def save(vote: UpVote): Future[ResultSet] = {
    insert
      .value(_.subjectId, vote.subjectId)
      .value(_.voterId, vote.voterId)
      .value(_.vote, vote.vote)
      .future()
  }

  def getVotesBySubjectId(subjectId: String): Future[Seq[UpVote]] = {
    select.where(_.subjectId eqs subjectId).fetchEnumerator() run Iteratee.collect()
  }

  def getVoteBySubjectAndVoterId(subjectId: String, voterId: String): Future[Option[UpVote]] = {
    select.where(_.subjectId eqs subjectId).and(_.voterId eqs voterId).one();
  }

  def deleteVoteBySubjectAndVoterId(subjectId: String, voterId: String): Future[ResultSet] = {
    delete.where(_.subjectId eqs subjectId).and(_.voterId eqs voterId).future();
  }
}
