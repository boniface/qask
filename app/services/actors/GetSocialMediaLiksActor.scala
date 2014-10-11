package services.actors

import akka.actor.{Actor, Props}
import akka.event.Logging
import services.ProcessSocialMediaLinks
import services.messages.Messages.{PostSocialMediaContent, Zone}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/10/11.
 */
class GetSocialMediaLiksActor extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case Zone(zone) => {
      val fetchContent = context.actorOf(Props[FetchSocialMediaContent])
      ProcessSocialMediaLinks.getSocialMediaFeed(zone) map (
        socialMediaFeeds =>
          socialMediaFeeds foreach (
            feed => {
              fetchContent ! PostSocialMediaContent(ProcessSocialMediaLinks.fetchLinks(feed.feedLink), feed)
            })
        )
    }
    case _ => log.info("received unknown message")
  }
}
