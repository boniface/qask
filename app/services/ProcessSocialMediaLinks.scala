package services

import java.net.URL

import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.io.{SyndFeedInput, XmlReader}
import conf.Util
import domain.{Post, SocialMediaFeed}

import scala.collection.JavaConverters._
import scala.concurrent.Future

/**
 * Created by hashcode on 2014/10/09.
 */
object ProcessSocialMediaLinks {

  def getSocialMediaFeed(zone: String): Future[Seq[SocialMediaFeed]] = {
    SmFeedsService.getFeedsByZone(zone)
  }

  def fetchLinks(feedurl: String): List[SyndEntry] = {
    val sfi = new SyndFeedInput()
    val feed = sfi.build(new XmlReader(new URL(feedurl)))
    feed.getEntries.asScala.toList
  }

  def postSocialMediaContent(links: List[SyndEntry], feed: SocialMediaFeed) = {
    links foreach (entry => {
      val post = Post(
        feed.zone,
        Util.md5Hash(entry.getLink),
        feed.feedSite,
        entry.getPublishedDate,
        entry.getTitle(),
        entry.getDescription.getValue,
        FetchContent.getMetaKeywords(entry.getDescription.getValue),
        FetchContent.getMedecription(entry.getDescription.getValue),
        entry.getLink,
        "no image",
        FetchContent.getPrettySeo(entry.getTitle()),
        "image",
        FetchContent.getCaption(),
        feed.siteCode
      )
      PostsService.create(post)
    })
  }
}
