package controllers

import models.CommentModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import respository.CommentRepository
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/23.
 */
object CommentController extends Controller{
  val service = CommentRepository

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val commentModel = Json.fromJson[CommentModel](input).get
      val comms = commentModel.getDomain()
      val comment = comms.copy(ipaddress = request.remoteAddress)
      val results = service.save(comment)
      results.map(result =>
        Ok(Json.toJson("OK"))
      )
  }

  def findCommentById(id: String) = Action.async {
    request =>
      val comments = service.getCommentsByAnswerId(id)
      comments map (quest => Ok(Json.toJson(quest)))
  }

}
