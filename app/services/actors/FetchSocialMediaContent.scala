package services.actors

import akka.actor.Actor
import services.ProcessSocialMediaLinks
import services.messages.Messages.PostSocialMediaContent

/**
 * Created by hashcode on 2014/10/11.
 */
class FetchSocialMediaContent extends Actor {
  override def receive: Receive = {
    case PostSocialMediaContent(links, feed) => {
      ProcessSocialMediaLinks.postSocialMediaContent(links, feed)
    }
  }
}
