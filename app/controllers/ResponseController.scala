package controllers

import conf.Util
import domain.Stats
import models.ResponseModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.ResponseService

import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Created by hashcode on 2014/07/23.
 */
object ResponseController extends Controller {

  def create(zone: String) = Action.async(parse.json) {
    request =>
      val input = request.body
      val responseModel = Json.fromJson[ResponseModel](input).get
      val ans = responseModel.getDomain()
      val answer = ans.copy(ipaddress = request.remoteAddress)
      val results = ResponseService.save(answer)
      ResponseService.countStat(Stats(ans.id, Util.RESPONSE.toString, 1L))
      results.map(result =>
        Ok(Json.toJson(answer))
      )
  }

  def getResponsesById(zone: String, id: String) = Action.async {
    request =>
      val responses = ResponseService.getResponseById(zone, id)
      responses map (quest => {
        Ok(Json.toJson(quest))
      })
  }

  def updateResponse(id: String, response: String) = Action.async {
    ResponseService.updateResponse(id, response) map (quest => {
      Ok(Json.toJson("OK"))
    })


  }


}
