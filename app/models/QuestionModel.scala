package models

import java.util.{Date, UUID}

import domain.Question
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/16.
 */
case class QuestionModel(title: String, detail: String, email: String, screenName: String) {
  def getDomain(): Question = QuestionModel.domain(this)
}

object QuestionModel {
  implicit val questionFmt = Json.format[QuestionModel]

  def domain(model: QuestionModel) = {
    Question(
      md5Hash(UUID.randomUUID().toString()),
      "",
      new Date,
      model.title,
      model.detail,
      model.email,
      model.screenName)
  }

  def md5Hash(text: String): String = {
    java.security.MessageDigest.getInstance("MD5").digest(text.getBytes()).map(0xFF & _).map {
      "%02x".format(_)
    }.foldLeft("") {
      _ + _
    }
  }
}
