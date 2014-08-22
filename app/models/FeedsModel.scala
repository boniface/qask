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

package models

import java.util.UUID

import conf.Util
import domain.Feed
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/12.
 */
case class FeedsModel(
                       feedLink: String,
                       feedType: String,
                       feedSite: String,
                       siteLogo: String) {
  def getDomain(): Feed = FeedsModel.domain(this)
}

object FeedsModel {
  implicit val roleFmt = Json.format[FeedsModel]

  def domain(model: FeedsModel) = {
    Feed("zone",Util.md5Hash(UUID.randomUUID().toString()), model.feedLink, model.feedType, model.feedSite, model.siteLogo)
  }
}