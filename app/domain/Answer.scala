package domain

import java.util.UUID

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/09.
 */
case class Answer(
                     questionId: String,
                     id: UUID,
                     date: DateTime,
                     answer: String,
                     email: String,
                     screenName: String,
                     ipaddress: String)

object Answer {
  implicit val answerFmt = Json.format[Answer]
}
