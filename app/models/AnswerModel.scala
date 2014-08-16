package models

import java.util.UUID

import conf.Util
import domain.{Comment, Response}
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/23.
 */
case class AnswerModel( questionId: String,
                        answer: String,
                        email: String,
                        screenName: String){
  def getDomain(): Response = AnswerModel.domain(this)
}

object AnswerModel{
  implicit val ansFmt = Json.format[AnswerModel]
  def domain(model: AnswerModel) = {
    val comments = Set[Comment]()
    Response(
      model.questionId,
      Util.md5Hash(UUID.randomUUID().toString())
      , new DateTime,
      model.answer,
      model.email,
      model.screenName,
      model.email)
  }

}
