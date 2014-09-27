package services.actors

import akka.actor.{Props, Actor}
import services.LinksService
import services.messages.Messages._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/09/25.
 */
class GetLinksActor extends Actor{

  override def receive: Receive = {
    case Zone(zone) => {
      LinksService.getLatestLinks(zone) map (links => {
        if(links.size>0){
          val fetch = context.actorOf(Props[FetchContentActor])
          fetch ! Links(links)
        }
      })
    }
    case _ => println("Hey I don't get This")
  }
}
