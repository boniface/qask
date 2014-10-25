package services.actors

import akka.actor.{Actor, Props}
import respository.CustomLinkRepository
import services.messages.Messages.{CustomPostLinks, Zone}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/10/25.
 */
class FetchCustomLinksActor extends Actor {

  override def receive: Receive = {
    case Zone(zone) => {
      CustomLinkRepository.getLatestLinks(zone) map (customLinks => {
        if (customLinks.size > 0) {
          val fetch = context.actorOf(Props[PostCustomLinksActor])
          fetch ! CustomPostLinks(customLinks)
        }
      })
    }
    case _ => println("No Links Available")
  }
}