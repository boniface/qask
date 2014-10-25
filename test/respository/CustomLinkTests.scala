package respository

import org.scalatest.{FeatureSpec, GivenWhenThen}
import services.customfetch.FetchFeedLinks
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
 * Created by hashcode on 2014/07/27.
 */
class CustomLinkTests extends FeatureSpec with GivenWhenThen {

  feature(" Add Records to the Respository") {

    info(" As a Account I want to Add a Record to the Database")
    info(" So that I can see the result in the DB")
    info("")
    scenario("Create Tables in The Database ") {

      Given(" The Stab to the Database")
      val repo = CustomFeedRepository
      val repFetch = FetchFeedLinks
      val customFeeds = Await.result(repo.getFeedsByZone("ZM"), 5000 millis)
      customFeeds foreach(customFeed=> {
        val links = repFetch.getLinks(customFeed.feedLink,"news","ZM")
        val filterLinks = repFetch.filterLinks(links)
        filterLinks foreach ( link => println("The is is a",customFeed.id))
        repFetch.postLinks(filterLinks,customFeed)
      })





//      val links = FetchFeedLinks.getLinks("http://postzambia.com/search.php?cmd=category&catid=70", "news")
//      val results = FetchFeedLinks.filterLinks(links)
//      results foreach (link => println(" The Link is ", link.url, " Title ", link.title))
    }
  }
}