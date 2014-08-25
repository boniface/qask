package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/08/25.
 */
case class User( email: String,
                screenName: String,
                authcode: String,
                social: Map[String, String])


object JsonFormats {
  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val userFormat = Json.format[User]
}