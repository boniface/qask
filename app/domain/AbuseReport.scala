package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/09.
 */
case class AbuseReport(topicId: String,
                       comment: Option[String],
                       userId: String)

object AbuseReport {
  implicit val abuseFmt = Json.format[AbuseReport]
}


