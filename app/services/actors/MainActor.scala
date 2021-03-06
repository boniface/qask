package services.actors

import akka.actor.{Actor, Props}
import akka.event.Logging
import services.ZoneService
import services.customfetch.FetchCustomFeedLinks
import services.messages.Messages._

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/09/15.
 */
class MainActor extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case Start(start) => {
      val latestLinks = context.actorOf(Props[GetLinksActor])
      val socialMediaLinks = context.actorOf(Props[GetSocialMediaLiksActor])
      val customLinks = context.actorOf(Props[GetCustomLinksActor])
      val fetchCustomLinks = context.actorOf(Props[FetchCustomLinksActor])
      ZoneService.getZones map (zones => zones foreach (
        zone => {
          latestLinks ! Zone(zone.code)
          socialMediaLinks ! Zone(zone.code)
          customLinks ! Zone(zone.code)
          fetchCustomLinks ! Zone(zone.code)
        })
        )
    }
    case _ => log.info("received unknown message")
  }
}
