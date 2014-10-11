package services.messages

import com.rometools.rome.feed.synd.SyndEntry
import domain.{SocialMediaFeed, Post, Link}

/**
 * Created by hashcode on 2014/09/27.
 */
object Messages {
  case class Start(message:String)
  case class Zone(zone:String)
  case class Links(links:Seq[Link])
  case class PostContent(post:Post)
  case class PostSocialMediaContent(links: List[SyndEntry], feed: SocialMediaFeed)

}
