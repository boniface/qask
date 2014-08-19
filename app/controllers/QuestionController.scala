package controllers


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

  def create(zone:String) = Action.async(parse.json) {
    request =>
      val input = request.body
      val questionModel = Json.fromJson[QuestionModel](input).get
      val quest = questionModel.getDomain()
      val question = quest.copy(zone=zone)
      val results = QuestionService.save(question)
      results.map(result =>
        Ok(Json.toJson(question))
      )
  }
  def getQuestionById(zone:String,id: String) = Action.async {
    request =>
      val question = QuestionService.getQuestionById(zone,id)
      question map(q => q match {
        case Some(q) =>QuestionService.countStat(Stats(id,"question",1L))
        case None => None
      })
      question map (quest =>
        Ok(Json.toJson(quest)))
  }

  def findAll(zone:String) = Action.async {
    request =>
      val questions = QuestionService.getQuestionsByZone(zone)
      questions map (quests => {
        Ok(Json.toJson(quests))
      })
  }

  def getStats(questionId:String,item:String)=Action.async{
    request =>
     val stats = QuestionService.getStats(questionId,item)
     stats map (stat => Ok(Json.toJson(stat match {
       case Some(stat)=>stat.counter
       case None => 0})))
  }
}

