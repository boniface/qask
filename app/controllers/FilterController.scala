package controllers


import models.{FilterExpressionModel, ZoneModel}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.ZoneService

/**
 * Created by hashcode on 2014/09/17.
 */
object FilterController extends Controller{

//  def create = Action(parse.json) {
//    request =>
//      val input = request.body
//      val filterModel = Json.fromJson[FilterExpressionModel](input).get
//      //Save into Redis Service
//       Ok("OK")
//  }
//
//
//  def getZoneById(id: String) = Action.async {
//    request =>
//      ZoneService.getZone(id) map (zone => Ok(Json.toJson(zone)))
//  }
//  def getZones = Action.async {
//    request =>
//      ZoneService.getZones map (zones => Ok(Json.toJson(zones)))
//  }
//
//  def deleteZone(id:String) = Action.async {
//    request =>
//      ZoneService.deleteZone(id) map (_ => Ok(Json.toJson("OK")))
//  }
//
//  def updateZoneStatus(id:String, status:String) = Action.async {
//    request =>
//      ZoneService.updateZoneStatus(id,status) map (_ => Ok(Json.toJson("OK")))
//  }

}
