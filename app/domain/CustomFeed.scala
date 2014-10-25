package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/10/17.
 */
case class CustomFeed(zone: String,
                      siteCode: String,
                      id: String,
                      feedLink: String,
                      feedSite: String,
                      siteLogo: String

                       )

object CustomFeed {
  implicit val customFeedFmt = Json.format[CustomFeed]

}
