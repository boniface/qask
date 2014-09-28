package services.actors

import akka.actor.{Props, ActorRef, Actor}
import akka.actor.Actor.Receive

/**
 * Created by hashcode on 2014/09/25.
 */
class ContentDisplayActor(out: ActorRef)  extends Actor{
  override def receive: Receive ={
    case msg: String =>
      out ! ("I received your message: " + msg)

  }
}

object ContentDisplayActor {
  def props(out: ActorRef) = Props(new ContentDisplayActor(out))
}
