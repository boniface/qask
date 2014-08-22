/*
 * Copyright (c) 2014 Hashcode (Z) Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers



import models.FeedsModel
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.FeedsService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/12.
 */
object FeedsController extends Controller {

  def create(zone:String) = Action.async(parse.json) {

    request =>
      val input = request.body
      val feedsModel = Json.fromJson[FeedsModel](input).get

      val f = feedsModel.getDomain()
      val feed = f.copy(zone=zone)
      val results = FeedsService.save(feed)
      results.map(result =>
        Ok(Json.toJson(feed)))
  }

  def update(zone:String,feedId:String) = Action.async(parse.json) {
    Logger.info("This has been Called ")
    request =>
      val input = request.body
      val feedsModel = Json.fromJson[FeedsModel](input).get
      val f = feedsModel.getDomain()
      Logger.info("FEED CREATED  has been Called ")
      val feed = f.copy(zone=zone,id = feedId)
      val results = FeedsService.save(feed)
      results.map(result =>
        Ok(Json.toJson(result.isExhausted)))
  }
  def getFeedById(zone:String,id: String) = Action.async {
    request =>
        FeedsService.getFeedById(zone,id) map (feed => Ok(Json.toJson(feed)))
  }
  def getFeeds = Action.async {
    request =>
      FeedsService.getFeeds map (feeds => Ok(Json.toJson(feeds)))
  }
  def getFeedsByZone(zone: String) = Action.async {
    request =>
      FeedsService.getFeedsByZone(zone) map (feeds => Ok(Json.toJson(feeds)))
  }

  def deleteFeed(zone:String, id:String) = Action.async {
    request =>
      FeedsService.delete(zone,id) map (_ => Ok(Json.toJson("OK")))
  }
}
