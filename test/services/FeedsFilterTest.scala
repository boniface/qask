package services

import domain.{Zone, SocialMediaFeed}
import org.scalatest.{GivenWhenThen, FeatureSpec}
import respository.SmFeedsRespository

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


/**
 * Created by hashcode on 2014/10/08.
 */
class FeedsFilterTest extends FeatureSpec with GivenWhenThen {
  feature(" Set up UpVote Table") {
    info("As a Deployer")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the Cassandra")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      //      val links = LinksService.getLatestLinks("ZM")
      //      links map (link => link foreach (l => println(" The links is ", l.url)))
      val feed = SocialMediaFeed("ZM","12","https://www.facebook.com/feeds/page.php?format=rss20&id=1512926715599838","RSS","www.postzambia.com","logo","PS")
      SmFeedsService.save(feed)

      val res = ProcessSocialMediaLinks.getSocialMediaFeed("ZM")
      val feeds = Await.result(res, 5000 millis)

      feeds foreach( zone => {
        val prop = ProcessSocialMediaLinks.fetchLinks(zone.feedLink)
        ProcessSocialMediaLinks.postSocialMediaContent(prop,zone)
        prop foreach(link => {
          println("The link is ",link.getLink)
          println("The Title is ",link.getTitle)
          println("The Markup is ",link.getDescription.getValue)
        })

      })



//      LinksService.getLatestLinks("ZM") map (links =>{
//        links foreach(link =>{
//          println(" The Link is ","SITECOE "+link.siteCode+" "+link.url)
//        })
//      })

    }
  }
}
