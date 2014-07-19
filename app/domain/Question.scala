package domain

import java.util.{Date, UUID}

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/16.
 */
case class Question(
                     id: String,
                     date: Date,
                     title: String,
                     detail: String,
                     email: String,
                     screenNane:String
                     )

object Question {
  implicit lazy val questFmt = Json.format[Question]
}
