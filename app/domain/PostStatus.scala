package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/11.
 */
case class PostStatus(postId:String, status:String)

object PostStatus {
  implicit val statusFmt = Json.format[PostStatus]
}


