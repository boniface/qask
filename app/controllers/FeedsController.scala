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

import java.util.UUID


import models.FeedsModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.Feedsservice

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/12.
 */
object FeedsController extends Controller {

  val service = new Feedsservice

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val feedsModel = Json.fromJson[FeedsModel](input).get
      val feed = feedsModel.getDomain()
      val results = service.save(feed)
      results.map(result =>
        Ok(Json.toJson(result.isExhausted)))
  }

  def findById(id: String) = Action.async {
    request =>
      val feed = service.getFeedById(id)
      feed map (f => Ok(Json.toJson(f)))
  }

  //
  def findAll = Action.async {
    request =>
      val feeds = service.getAllFeeds
      feeds map (feed => {
        Ok(Json.toJson(feed))
      })
  }

  def findBySite(siteId: String) = Action.async {
    request =>
      val feeds = service.getFeedsbySite(siteId)
      feeds map (feed => {
        Ok(Json.toJson(feed))
      })
  }

}
