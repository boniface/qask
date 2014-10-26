package services

import domain.{CustomFeed, SocialMediaFeed}
import respository.{CustomFeedRepository, SocialMediaFeedsRespository}

/**
 * Created by hashcode on 2014/10/08.
 */
object CustomFeedsService {
  def getAllCustomFeeds = {
    CustomFeedRepository.getFeeds
  }

  def save(feed: CustomFeed) = {
    CustomFeedRepository.save(feed)
  }

  def getFeedById(zone: String, sitecode:String, id: String) = {
    CustomFeedRepository.getFeedById(zone, sitecode,id)
  }

  def getFeedsByZone(zone: String) = {
    CustomFeedRepository.getFeedsByZone(zone)
  }

  def delete(zone: String, id: String) = {
    CustomFeedRepository.deleteFeed(zone, id)
  }

}
