package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/11.
 */
case class Status(subjectId:String, status:String)

object Status {
  implicit val statusFmt = Json.format[Status]
}


