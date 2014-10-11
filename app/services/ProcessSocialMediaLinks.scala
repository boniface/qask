package services

import java.net.URL

import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.io.{SyndFeedInput, XmlReader}
import conf.Util
import domain.{Post, SocialMediaFeed}
import org.jsoup.Jsoup

import scala.collection.JavaConverters._
import scala.concurrent.Future
import scala.util.matching.Regex

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
        cleanText(entry.getTitle()),
        cleanText(entry.getDescription.getValue),
        FetchContent.getMetaKeywords(cleanText(entry.getTitle)),
        FetchContent.getMedecription(cleanText(entry.getDescription.getValue)),
        entry.getLink,
        getImage(entry.getLink),
        FetchContent.getPrettySeo(entry.getTitle()),
        getImage(entry.getLink),
        FetchContent.getCaption(),
        feed.siteCode
      )
      PostsService.create(post)
    })
  }

  def getImage(url:String) ={
    Jsoup.connect(url).get().select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("src")
  }
  def cleanText(title:String)={
   val text =new Regex("&#039;").replaceAllIn(new Regex("<br />").replaceAllIn(title, " <br/>"), "'")
    new Regex("&#x2019;").replaceAllIn(new Regex("&#x2018;").replaceAllIn(text, "'"), "'")
  }


}
