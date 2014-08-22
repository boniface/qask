package controllers

import controllers.QuestionController._
import play.api.libs.json.Json
import play.api.mvc.Action
import services.StatsService
import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Created by hashcode on 2014/08/22.
 */
object StatsController {
  def getStats(item:String,subjectId:String)=Action.async{
    request =>
      StatsService.getStats(item,subjectId) map
        (stat => Ok(Json.toJson(stat match {
        case Some(stat)=>stat.counter
        case None => 0})))
  }

}
