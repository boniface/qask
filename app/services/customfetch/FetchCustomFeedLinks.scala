package services.customfetch

import java.util.Date

import conf.Util
import domain.{CustomFeed, CustomLink}
import org.jsoup.Jsoup
import respository.{CustomProcessedLinkskRepository, CustomFeedRepository, CustomLinkRepository}
import services.customfetch.customsites.Clink
import scala.concurrent.ExecutionContext.Implicits.global

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

/**
 * Created by hashcode on 2014/10/18.
 */
object FetchCustomFeedLinks {

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
        if(r.size>1) {
          plinks.+=(Clink(Util.getIntFromString(r(1)), link.attr("abs:href"), link.text(), zone))
        }
      })
      case Failure(ex) => scala.collection.mutable.MutableList[Clink]()
    }
      filterLinks(plinks)
  }

  private def filterLinks(links: scala.collection.mutable.MutableList[Clink]) = {
    links.filter(link => link.title.length > 12).sortBy(link => link.id).reverse take (20)
  }

  def postLinks(links: scala.collection.mutable.MutableList[Clink], feed: CustomFeed) = {
    links foreach (link => {
      val results = CustomProcessedLinkskRepository.getLinkById(Util.md5Hash(link.url))
      results map (result => {
        result match {
          case Some(clink) => None
          case None => {
            val value = CustomLink(
              link.zone,
              new Date(),
              Util.md5Hash(link.url),
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
