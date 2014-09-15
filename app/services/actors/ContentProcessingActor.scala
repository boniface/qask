package services.actors

import akka.actor.Actor
import akka.actor.Actor.Receive
import akka.event.Logging

/**
 * Created by hashcode on 2014/09/15.
 */

class ContentProcessingActor extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case "START" => log.info(" We Are Ready to Start")
    case _      => log.info("received unknown message")
  }
}
