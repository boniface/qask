package controllers

import models.RoleModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.RoleService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/07.
 */
object RoleController extends Controller {

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val roleModel = Json.fromJson[RoleModel](input).get
      val role = roleModel.getDomain()
      val results = RoleService.save(role)
      results.map(result =>
        Ok(Json.toJson(result.isExhausted)))
  }

  def findById(id: String) = Action.async {
    request =>
      val role = RoleService.findById(id)
      role map (rol => Ok(Json.toJson(rol)))
  }

  //
  def findAll() = Action.async {
    request =>
      val roles = RoleService.findAll()
      roles map (rol => {
        Ok(Json.toJson(rol))
      })
  }

}
