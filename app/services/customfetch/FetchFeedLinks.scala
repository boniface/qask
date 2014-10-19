package services.customfetch

import java.util.Date

import conf.Util
import domain.{CustomFeed, CustomLink}
import org.jsoup.Jsoup
import respository.{CustomFeedRepository, CustomLinkRepository}
import services.customfetch.qfm.Clink

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

/**
 * Created by hashcode on 2014/10/18.
 */
object FetchFeedLinks {

  def getFeeds(zone: String, siteCode: String) = {
    CustomFeedRepository.getFeedsBySite(zone, siteCode)
  }

  def getZoneFeeds(zone: String) = {
    CustomFeedRepository.getFeedsByZone(zone)
  }

  def getLinks(url: String, filter: String, zone: String): scala.collection.mutable.MutableList[Clink] = {
    val plinks = scala.collection.mutable.MutableList[Clink]()
    val links = Try(Jsoup
      .connect(url)
      .get()
      .select("a[href]")
      .asScala
      .filter(link => link.attr("abs:href").contains(filter))) match {
      case Success(result) => result foreach (link => {
        val r = link.attr("abs:href").split("=")
        plinks.+=(Clink(Util.getIntFromString(r(1)), link.attr("abs:href"), link.text(), zone))
      })
      case Failure(ex) => scala.collection.mutable.MutableList[Clink]()
    }
    plinks
  }

  def filterLinks(links: scala.collection.mutable.MutableList[Clink]) = {
    links.filter(link => link.title.length > 12).sortBy(link => link.id).reverse take (20)
  }

  def postLinks(links: scala.collection.mutable.MutableList[Clink], feed: CustomFeed) = {
    links foreach (link => {
      val result = CustomLinkRepository.getLinkById(link.zone, Util.md5Hash(link.url))
      result map (res => {
        res match {
          case Some => None
          case None => {
            val value = CustomLink(
              link.zone,
              Util.md5Hash(link.url),
              new Date(),
              link.url,
              feed.feedSite,
              feed.siteCode,
              link.title)
            CustomLinkRepository.save(value)
          }
        }
      })
    })
  }

}
