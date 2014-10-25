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

import java.util.Date

import conf.Util
import domain.{Post, Stats}
import respository.{SitePostRespository, ZonePostRespository, StatsRepository, PostRespository}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/12.
 */
object PostsService {
  def create(post: Post) ={
    PostRespository.save(post)
  }

  def getPostsByZone(zone: String) = {
   ZonePostRespository.getPostsByZone(zone)
  }

  def getPostById(zone:String,id: String) = {
    StatsRepository.statIncrement(Stats(id,Util.POST.toString,1L))
    PostRespository.getPostById(zone,id)
  }

  def getZonePostsByDate(zone: String, date: Date) = {
    ZonePostRespository.getZonePostsByDate(zone,date)
  }

  def getZonePostsByYesterday(zone: String, date: Date) = {
    ZonePostRespository.getZonePostsByYesterday(zone,date)
  }

  def getSitePosts(zone: String, domain: String) = {
    SitePostRespository.getSitePosts(domain)
  }

  def getSitePostsByDate(zone: String, domain: String, date: Date) = {
    SitePostRespository.getSitePostsByDate(domain,date)
  }

  def getSitePostsByYesterday(zone: String, domain: String, date: Date) = {
    SitePostRespository.getSitePostsByYesterday(domain,date)
  }

  def getZoneCustomPosts(zone: String, start: Date, end: Date) = {
    ZonePostRespository.getZoneCustomPosts(zone,start,end)
  }

  def getSiteCustomPosts(zone: String, domain: String, start: Date, end: Date)={
    SitePostRespository.getSiteCustomPosts(domain,start,end)
  }


}
