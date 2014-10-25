package services.actors

import akka.actor.{Props, Actor}
import services.PostsService
import services.customfetch.customsites.PostCustomContent
import services.messages.Messages._

/**
 * Created by hashcode on 2014/09/25.
 */
class PostContentActor extends Actor{
  override def receive: Receive = {
    case PostContent(post) => {
      PostsService.create(post)
    }

    case PostContentCustom(link) => PostCustomContent.postContent(link)
  }
}
