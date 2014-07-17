package domain

import java.util.UUID

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/16.
 */
case class Question(
                     id: UUID,
                     date: DateTime,
                     title: String,
                     detail: String,
                     authourId: String
                     )

object Question {
  implicit lazy val questFmt = Json.format[Question]

}
