package services.actors

import akka.actor.{Props, Actor}
import services.FetchContent
import services.messages.Messages._

import scala.util.Try

/**
 * Created by hashcode on 2014/09/25.
 */
class FetchContentActor extends Actor{

  override def receive: Receive = {
    case Links(links)=>{
      links foreach(link => {
        val postContent = context.actorOf(Props[PostContentActor])
        val post = Try{FetchContent.getContent(link)}
        post map ( p =>
          postContent ! PostContent(p))

      })
    }
    case _ => println(" No Links received !!!")
  }
}
