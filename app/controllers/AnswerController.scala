package controllers

import conf.Util
import domain.Stats
import models.AnswerModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import respository.StatsRepository
import services.ResponseService

import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Created by hashcode on 2014/07/23.
 */
object AnswerController extends Controller {

  def create(zone:String) = Action.async(parse.json) {
    request =>
      val input = request.body
      val answerModel = Json.fromJson[AnswerModel](input).get
      val ans = answerModel.getDomain()
      val answer = ans.copy(ipaddress = request.remoteAddress)
      val results = ResponseService.save(answer)
      ResponseService.countStat(Stats(ans.id,Util.RESPONSE.toString,1L))
      results.map(result =>
        Ok(Json.toJson(answer))
      )
  }

  def findAnswersByQuestionId(id: String) = Action.async {
    request =>
      val answers = ResponseService.getResponseById(id)
      answers map (quest => {
        Ok(Json.toJson(quest))
      })
  }


}
