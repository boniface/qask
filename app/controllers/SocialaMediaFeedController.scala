package controllers

import controllers.FeedsController._
import models.{SocialMediaFeedsModel, FeedsModel}
import play.api.libs.json.Json
import play.api.mvc.BodyParsers.parse
import play.api.mvc.{Action, Controller}
import services.{SocialMediaFeedsService, FeedsService}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/10/11.
 */
object SocialaMediaFeedController extends Controller {

  def create(zone:String) = Action.async(parse.json) {

    request =>
      val input = request.body
      val feedsModel = Json.fromJson[SocialMediaFeedsModel](input).get

      val f = feedsModel.getDomain()
      val feed = f.copy(zone=zone)
      val results = SocialMediaFeedsService.save(feed)
      results.map(result =>
        Ok(Json.toJson(feed)))
  }

  def update(zone:String,feedId:String) = Action.async(parse.json) {
    request =>
      val input = request.body
      val feedsModel = Json.fromJson[SocialMediaFeedsModel](input).get
      val f = feedsModel.getDomain()
      val feed = f.copy(zone=zone,id = feedId)
      val results = SocialMediaFeedsService.save(feed)
      results.map(result =>
        Ok(Json.toJson(feed)))
  }
  def getFeedById(zone:String,id: String) = Action.async {
    request =>
      SocialMediaFeedsService.getFeedById(zone,id) map (feed => Ok(Json.toJson(feed)))
  }
  def getAllSocialMediaFeeds = Action.async {
    request =>
      SocialMediaFeedsService.getAllSocialMediaFeeds map (feeds => Ok(Json.toJson(feeds)))
  }
  def getFeedsByZone(zone: String) = Action.async {
    request =>
      SocialMediaFeedsService.getFeedsByZone(zone) map (feeds => Ok(Json.toJson(feeds)))
  }

  def deleteFeed(zone:String, id:String) = Action.async {
    request =>
      SocialMediaFeedsService.delete(zone,id) map (_ => Ok(Json.toJson("OK")))
  }
}
