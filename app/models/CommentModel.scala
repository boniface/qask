package models

import java.util.UUID

import conf.Util
import domain.Comment
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/23.
 */
case class CommentModel(responseId: String, comment: String, email: String, screenName: String,subjectId:String) {
  def getDomain(): Comment = CommentModel.domain(this)
}

object CommentModel {
  implicit val commFmt = Json.format[CommentModel]
  def domain(model: CommentModel) = {
    Comment(
      model.responseId,
      Util.md5Hash(UUID.randomUUID().toString()),
      "zone",
      new DateTime,
      model.comment,
      model.email,
      model.screenName,
      model.email
   )
  }
}
