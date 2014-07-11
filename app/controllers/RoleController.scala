package controllers

import java.util.UUID

import models.RoleModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.RoleService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/07.
 */
object RoleController extends Controller {
  val service = new RoleService

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val roleModel = Json.fromJson[RoleModel](input).get
      val role = roleModel.getDomain()
      val results = service.save(role)
      results.map(result =>
        Ok(Json.toJson(result.isExhausted)))
  }

  def findById(id: UUID) = Action.async {
    request =>
      val role = service.findById(id)
      role map (rol => Ok(Json.toJson(rol)))
  }

  //
  def findAll() = Action.async {
    request =>
      val roles = service.findAll()
      roles map (rol => {
        Ok(Json.toJson(rol))
      })
  }

}
