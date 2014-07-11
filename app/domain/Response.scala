package domain

import java.util.UUID

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/09.
 */
case class Response(
                     topicId: String,
                     id: UUID,
                     date: DateTime,
                     commnent: String,
                     userId: String,
                     seo: String,
                     ipaddress: String)

object Response {
  implicit val responseFmt = Json.format[Response]
}
