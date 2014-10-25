package services.messages

import com.rometools.rome.feed.synd.SyndEntry
import domain._
import services.customfetch.customsites.Clink

/**
 * Created by hashcode on 2014/09/27.
 */
object Messages {
  case class Start(message:String)
  case class Zone(zone:String)
  case class Links(links:Seq[Link])
  case class PostContent(post:Post)
  case class PostSocialMediaContent(links: List[SyndEntry], feed: SocialMediaFeed)
  case class CustomFeeds(feeds:Seq[CustomFeed])
  case class CustomFeedLinks(links:scala.collection.mutable.MutableList[Clink], feed:CustomFeed)
  case class CustomPostLinks(links:Seq[CustomLink])
  case class PostContentCustom(link:CustomLink)

}
