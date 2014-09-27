package services.messages

import domain.{Post, Link}

/**
 * Created by hashcode on 2014/09/27.
 */
object Messages {
  case class Start(message:String)
  case class Zone(zone:String)
  case class Links(links:Seq[Link])
  case class PostContent(post:Post)

}
