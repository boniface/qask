package services.actors

import akka.actor.Actor
import akka.actor.Actor.Receive
import akka.event.Logging
import services.{ZoneService, PostsService}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/09/15.
 */

class ContentProcessingActor extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case "START" => {
      log.info(" We Are Ready to Start")
      ZoneService.getZones map (zone => zone foreach(v=> println("The Zone is ",v.code)))
    }
    case _      => log.info("received unknown message")
  }
}
