package domain

import java.util.UUID

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/09.
 */
case class Comment(
                          responseId: String,
                          id: String,
                          zone:String,
                          date: DateTime,
                          comment: String,
                          email: String,
                          screenName: String,
                          ipaddress: String)

object Comment {
  implicit val commentFmt = Json.format[Comment]
}
