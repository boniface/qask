package services.actors

import akka.actor.{Actor, Props}

import respository.CustomFeedRepository
import services.messages.Messages.{Zone, CustomFeeds}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/09/25.
 */
class GetCustomLinksActor extends Actor {

  override def receive: Receive = {
    case Zone(zone) => {
      CustomFeedRepository.getFeedsByZone(zone) map (customFeeds => {
        if (customFeeds.size > 0) {
          val fetch = context.actorOf(Props[FetchCustomFeedsActor])
          fetch ! CustomFeeds(customFeeds)
        }
      })
    }
    case _ => println("No Links Available")
  }
}
