package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/08/25.
 */
case class Account( email: String,
                screenName: String,
                authcode: String,
                social: Map[String, String])


object Account {
  // Generates Writes and Reads for Feed and Account thanks to Json Macros
  implicit val userFormat = Json.format[Account]
}