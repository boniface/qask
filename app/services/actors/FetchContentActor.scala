package services.actors

import akka.actor.{Props, Actor}
import services.FetchContent
import services.messages.Messages._

/**
 * Created by hashcode on 2014/09/25.
 */
class FetchContentActor extends Actor{

  override def receive: Receive = {
    case Links(links)=>{
      println(" We have the links Dude!!", links.size)
      links.par foreach(link => {
        val postContent = context.actorOf(Props[PostContentActor])
        val post = FetchContent.getContent(link)
        postContent ! PostContent(post)
      })
    }
    case _ => println(" No Links received !!!")
  }
}
