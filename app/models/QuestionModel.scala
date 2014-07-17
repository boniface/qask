package models

import java.util.UUID

import domain.Question
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/16.
 */
case class QuestionModel(title:String, detail:String,authorId:String)

object QuestionModel{
  implicit val questionFmt = Json.format[QuestionModel]
  def domain(model: QuestionModel) = {
    Question(UUID.randomUUID(),new DateTime,model.detail,model.title,model.authorId)
  }

}
