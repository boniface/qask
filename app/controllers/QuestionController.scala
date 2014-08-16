package controllers

import java.util.UUID

import domain.Stats
import models.QuestionModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.QuestionService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/17.
 */
object QuestionController extends Controller {
  val service = new QuestionService

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val questionModel = Json.fromJson[QuestionModel](input).get
      val question = questionModel.getDomain()
      val results = service.save(question)
      results.map(result =>
        Ok(Json.toJson(question))
      )
  }

  def findById(id: String) = Action.async {
    request =>
      val question = service.getQuestionById(id)
      question map(q => q match {
        case Some(q) =>service.countStat(Stats(id,request.domain,1L))
        case None => None
      })
      question map (quest =>
        Ok(Json.toJson(quest)))
  }

  //
  def findAll() = Action.async {
    request =>
      val questions = service.getAllQuestion
      questions map (quests => {
        Ok(Json.toJson(quests))
      })
  }

  def getStats(questionId:String)=Action.async{
    request =>
     val stats = service.getStats(questionId,request.domain)
     stats map (stat => Ok(Json.toJson(stat match {
       case Some(view)=>view.counter
       case None => 0})))

  }

 
}

