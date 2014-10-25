package services.actors

import akka.actor.{Actor, Props}
import services.customfetch.FetchCustomFeedLinks
import services.messages.Messages.{CustomFeeds, CustomFeedLinks}

/**
 * Created by hashcode on 2014/10/25.
 */
class FetchCustomFeedsActor extends Actor {

  override def receive: Receive = {
    case CustomFeeds(feeds) => {
      feeds foreach (feed => {
        val links = FetchCustomFeedLinks.getLinks(feed.feedLink, feed.filter, feed.zone)
        val fetch = context.actorOf(Props[PostCustomLinksActor])
        fetch ! CustomFeedLinks(links, feed)
      })
    }
    case _ => println("No Links Available")
  }
}
