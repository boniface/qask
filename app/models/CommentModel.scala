package models

import java.util.UUID

import domain.Comment
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/23.
 */
case class CommentModel(answerId: String, comment: String, email: String, screenName: String) {
  def getDomain(): Comment = CommentModel.domain(this)
}

object CommentModel {

  implicit val commFmt = Json.format[CommentModel]

  def domain(model: CommentModel) = {
    Comment(
      model.answerId,
      md5Hash(UUID.randomUUID().toString())
      , new DateTime,
      model.comment,
      model.email,
      model.screenName,
      model.email)
  }

  def md5Hash(text: String): String = {
    java.security.MessageDigest.getInstance("MD5").digest(text.getBytes()).map(0xFF & _).map {
      "%02x".format(_)
    }.foldLeft("") {
      _ + _
    }
  }

}
