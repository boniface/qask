package domain

import java.nio.ByteBuffer

import play.api.libs.json.Json

import scala.reflect.io.File

/**
 * Created by hashcode on 2014/10/17.
 */
case class CustomFeed(zone:String,
                  id: String,
                  siteCode:String,
                  feedLink: String,
                  feedSite: String,
                  siteLogo: String

                 )

object CustomFeed {
  implicit val customFeedFmt = Json.format[CustomFeed]

}
