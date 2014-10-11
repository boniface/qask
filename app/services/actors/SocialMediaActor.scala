package services.actors

import akka.actor.{Actor, Props}
import akka.event.Logging
import services.ZoneService
import services.messages.Messages.{Start, Zone}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/09/25.
 */
class SocialMediaActor extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case Start(start) => {
      val latestLinks = context.actorOf(Props[GetSocialMediaLiksActor])
      ZoneService.getZones map (
        zone => zone foreach (
          v => latestLinks ! Zone(v.code))
        )
    }
    case _ => log.info("received unknown message")
  }
}

