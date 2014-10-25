package respository

import domain.CustomFeed
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by hashcode on 2014/07/27.
 */
class CustomFeedTest extends FeatureSpec with GivenWhenThen {

  feature(" Add Records to the Respository"){

    info(" As a Account I want to Add a Record to the Database")
    info(" So that I can see the result in the DB")
    info("")
    scenario("Create Tables in The Database "){

      Given(" The Stab to the Database")
      val repo = CustomFeedRepository

      val feed1 = CustomFeed(zone="ZM","1","PT","http://postzambia.com/search.php?cmd=category&catid=67","www.postzambia.com","image")
      val feed2 = CustomFeed("ZM","2","PT","http://postzambia.com/search.php?cmd=category&catid=3","www.postzambia.com","image")
      val feed3 = CustomFeed("ZM","3","PT","http://postzambia.com/search.php?cmd=category&catid=70","www.postzambia.com","image")

//      val comment = Comment("100","1",new DateTime,"This is a Coment","bojovi@jkd.com","KAYADS","123.4.5.67")

      repo.save(feed1)
      repo.save(feed2)
      repo.save(feed3)

//      repo.postComment("123","100",comment)

//      val answers = repo.getResponseBySubjectId("27090ba50639e5a6fc127140cac9fd78")

//      val ans = Await.result(repo.getResponseBySubjectId("27090ba50639e5a6fc127140cac9fd78"), 5000 millis)






    }

  }

}
