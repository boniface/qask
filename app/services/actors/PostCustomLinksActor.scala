package services.actors

import akka.actor.{Actor, Props}
import services.customfetch.FetchCustomFeedLinks
import services.messages.Messages.{CustomFeedLinks, CustomPostLinks, PostContentCustom}

/**
 * Created by hashcode on 2014/10/25.
 */
class PostCustomLinksActor extends Actor {
  override def receive: Receive = {
    case CustomFeedLinks(links, feed) => FetchCustomFeedLinks.postLinks(links, feed)
    case CustomPostLinks(links) => {
      val fetch = context.actorOf(Props[PostContentActor])
      links foreach (link => fetch ! PostContentCustom(link))
    }
    case _ => println("No Links Available")
  }
}
