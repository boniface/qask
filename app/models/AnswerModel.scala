package models

import java.util.UUID

import domain.Answer
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/23.
 */
case class AnswerModel( questionId: String,
                        answer: String,
                        email: String,
                        screenName: String){
  def getDomain(): Answer = AnswerModel.domain(this)
}

object AnswerModel{
  implicit val ansFmt = Json.format[AnswerModel]
  def domain(model: AnswerModel) = {
    Answer(
      model.questionId,
      md5Hash(UUID.randomUUID().toString())
      , new DateTime,
      model.answer,
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
