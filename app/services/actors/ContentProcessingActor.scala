package services.actors

import akka.actor.{Actor, Props}
import akka.event.Logging
import services.ZoneService
import services.messages.Messages._

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/09/15.
 */
class ContentProcessingActor extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case Start(start) => {
      val latestLinks = context.actorOf(Props[GetLinksActor])
      val socialMediaLinks = context.actorOf(Props[GetSocialMediaLiksActor])
      ZoneService.getZones map (zone => zone foreach (
        v => {
          latestLinks ! Zone(v.code)
          socialMediaLinks ! Zone(v.code)
        })
        )
    }
    case _ => log.info("received unknown message")
  }
}
