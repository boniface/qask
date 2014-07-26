package respository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PrimaryKey
import conf.DataConnection
import domain.DownVote

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/08.
 */
sealed class DownVoteRepository extends CassandraTable[DownVoteRepository, DownVote] {

  object subjectId extends StringColumn(this) with PartitionKey[String]

  object voterId extends StringColumn(this) with PrimaryKey[String]

  object vote extends StringColumn(this)

  override def fromRow(row: Row): DownVote = {
    DownVote(subjectId(row), voterId(row), vote(row))
  }
}

object DownVoteRepository extends DownVoteRepository with DataConnection {
  override lazy val tableName = "downvotes"

  def save(vote: DownVote): Future[ResultSet] = {
    insert
      .value(_.subjectId, vote.subjectId)
      .value(_.voterId, vote.voterId)
      .value(_.vote, vote.vote)
      .future()
  }

  def getVotesBySubjectId(subjectId: String): Future[Seq[DownVote]] = {
    select.where(_.subjectId eqs subjectId).fetchEnumerator() run Iteratee.collect()
  }


  def getVoteBySubjectAndVoterId(subjectId: String, voterId: String): Future[Option[DownVote]] = {
    select.where(_.subjectId eqs subjectId).and(_.voterId eqs voterId).one();
  }

  def deleteVoteBySubjectAndVoterId(subjectId: String, voterId: String): Future[ResultSet] = {
    delete.where(_.subjectId eqs subjectId).and(_.voterId eqs voterId).future();
  }
}