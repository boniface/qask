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


import conf.Util
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.PostsService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/12.
 */
object PostsController extends Controller {


  def getPostsByZone(zone: String) = Action.async {
    request =>
      PostsService.getPostsByZone(zone) map
        (posts => Ok(Json.toJson(posts)))
  }

  def getPostById(zone: String, id: String) = Action.async {
    request =>
      PostsService.getPostById(zone, id) map
        (post => Ok(Json.toJson(post)))
  }

  def getZonePostsByDate(zone: String, date:String) = Action.async {

    request =>
      PostsService.getZonePostsByDate(zone, Util.getDate(date)) map
        (posts => Ok(Json.toJson(posts)))
  }

  def getSitePosts(zone: String, domain: String) = Action.async {
    request =>
      PostsService.getSitePosts(zone, domain) map
        (posts => Ok(Json.toJson(posts)))
  }

  def getSitePostsByDate(zone: String, domain: String, date: String) = Action.async {
    request =>
      PostsService.getSitePostsByDate(zone, domain, Util.getDate(date)) map
        (posts => Ok(Json.toJson(posts)))
  }

  def getZoneCustomPosts(zone: String, start: String, end: String) = Action.async {
    request =>
      PostsService.getZoneCustomPosts(zone, Util.getDateFromString(start), Util.getDateFromString(end)) map
        (posts => Ok(Json.toJson(posts)))
  }

  def getSiteCustomPosts(zone: String, domain: String, start: String, end: String) = Action.async {
    request =>
      PostsService.getSiteCustomPosts(zone, domain, Util.getDateFromString(start), Util.getDateFromString(end)) map
        (posts => Ok(Json.toJson(posts)))
  }


}
