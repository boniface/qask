package domain

import org.joda.time.DateTime
import play.api.libs.json.Json
import respository.CommentRepository

/**
 * Created by hashcode on 2014/07/09.
 */
case class Response(
                   subjectId: String,
                   id: String,
                   zone:String,
                   date: DateTime,
                   response: String,
                   email: String,
                   screenName: String,
                   ipaddress: String
                   )

object Response {
  implicit val answerFmt = Json.format[Response]



}
