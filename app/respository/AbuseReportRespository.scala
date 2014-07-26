package respository

/**
 * Created by hashcode on 2014/07/09.
 */


import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PrimaryKey
import conf.DataConnection
import domain.AbuseReport

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/09.
 */
sealed class AbuseReportRespository extends CassandraTable[AbuseReportRespository, AbuseReport] {

  object topicId extends StringColumn(this) with PartitionKey[String]

  object userId extends StringColumn(this) with PrimaryKey[String]

  object comment extends OptionalStringColumn(this)

  override def fromRow(row: Row): AbuseReport = {
    AbuseReport(topicId(row),
      comment(row),
      userId(row))
  }
}

object AbuseReportRespository extends AbuseReportRespository with DataConnection {
  override lazy val tableName = "abusereports"

  def save(moderate: AbuseReport): Future[ResultSet] = {
    insert
      .value(_.topicId, moderate.topicId)
      .value(_.comment, moderate.comment)
      .value(_.userId, moderate.userId)
      .future()
  }

  def getAbuseByTopic(topicId: String): Future[Seq[AbuseReport]] = {
    select.where(_.topicId eqs topicId).fetchEnumerator() run Iteratee.collect()
  }
}
