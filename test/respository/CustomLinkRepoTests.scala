package respository

import java.util.Date

import conf.Util
import domain.CustomLink
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by hashcode on 2014/07/27.
 */
class CustomLinkRepoTests extends FeatureSpec with GivenWhenThen {

  feature(" Add Records to the Respository") {

    info(" As a Account I want to Add a Record to the Database")
    info(" So that I can see the result in the DB")
    info("")
    scenario("Create Tables in The Database ") {

      Given(" The Stab to the Database")
      val repo = CustomLinkRepository

      val cl = CustomLink("ZM",new Date(),Util.md5Hash("www.test.com"),"www.test.com","PST","PST","Hellow")

      repo.save(cl)






//      val links = FetchFeedLinks.getLinks("http://postzambia.com/search.php?cmd=category&catid=70", "news")
//      val results = FetchFeedLinks.filterLinks(links)
//      results foreach (link => println(" The Link is ", link.url, " Title ", link.title))
    }
  }
}