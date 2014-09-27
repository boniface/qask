package services.actors

import akka.actor.{Props, Actor}
import akka.actor.Actor.Receive
import akka.event.Logging
import services.{ZoneService}
import scala.concurrent.ExecutionContext.Implicits.global
import services.messages.Messages._

/**
 * Created by hashcode on 2014/09/15.
 */
class ContentProcessingActor extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case Start(start) => {
      val latestLinks = context.actorOf(Props[GetLinksActor])
      ZoneService.getZones map (zone => zone foreach(
        v=> latestLinks ! Zone(v.code))
        )
    }
    case _  => log.info("received unknown message")
  }
}
