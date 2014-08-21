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

package services

import domain.Feed
import respository.FeedsRespository

/**
 * Created by hashcode on 2014/07/12.
 */
object FeedsService {

  def save(feed: Feed) = {
    FeedsRespository.save(feed)
  }
  def getFeedById(zone:String,id: String) = {
    FeedsRespository.getFeedById(zone,id)
  }
  def getFeedsByZone(zone:String) = {
    FeedsRespository.getFeedsByZone(zone)
  }
  def getFeeds = {
    FeedsRespository.getFeeds
  }
  def delete(zone:String,id: String) = {
    FeedsRespository.deleteFeed(zone,id)
  }

}
