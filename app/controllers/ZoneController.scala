package controllers

import models.ZoneModel
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.ZoneService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/08/28.
 */
object ZoneController extends Controller {

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val zoneModel = Json.fromJson[ZoneModel](input).get
      val zone = zoneModel.getDomain()
      val results = ZoneService.createZone(zone)
      results.map(result =>
        Ok(Json.toJson(zone)))
  }

  def update(feedId:String) = Action.async(parse.json) {
    request =>
      val input = request.body
      val zoneModel = Json.fromJson[ZoneModel](input).get
      val f = zoneModel.getDomain()
      val feed = f.copy(id = feedId)
      val results = ZoneService.updateZone(feed)
      results.map(result =>
        Ok(Json.toJson(feed)))
  }
  def getZoneById(id: String) = Action.async {
    request =>
      ZoneService.getZone(id) map (zone => Ok(Json.toJson(zone)))
  }
  def getZones = Action.async {
    request =>
      ZoneService.getZones map (zones => Ok(Json.toJson(zones)))
  }

  def deleteZone(id:String) = Action.async {
    request =>
      ZoneService.deleteZone(id) map (_ => Ok(Json.toJson("OK")))
  }

  def updateZoneStatus(id:String, status:String) = Action.async {
    request =>
      ZoneService.updateZoneStatus(id,status) map (_ => Ok(Json.toJson("OK")))
  }
}
