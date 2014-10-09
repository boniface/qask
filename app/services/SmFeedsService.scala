package services

import domain.{SocialMediaFeed, Feed}
import respository.{SmFeedsRespository, FeedsRespository}

/**
 * Created by hashcode on 2014/10/08.
 */
object SmFeedsService {
  def save(feed: SocialMediaFeed) = {
    SmFeedsRespository.save(feed)
  }
  def getFeedById(zone:String,id: String) = {
    SmFeedsRespository.getFeedByZone(zone,id)
  }
  def getFeedsByZone(zone:String) = {
    SmFeedsRespository.getAllZoneFeeds(zone)
  }
  def delete(zone:String,id: String) = {
    SmFeedsRespository.deleteFeed(zone,id)
  }

}
