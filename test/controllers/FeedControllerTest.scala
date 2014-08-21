package controllers

import com.google.gson.Gson
import domain.Feed
import models.FeedsModel
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.test.WithApplication

/**
 * Created by hashcode on 2014/08/21.
 */
@RunWith(classOf[JUnitRunner])
class FeedControllerTest extends Specification {
  val gson = new Gson()

  "Controllers" should {

    "Should Save Feed Object" in new WithApplication {
      val feed = FeedsModel("","rss","lusakatimes.com","var/imahges.jpg")


    }
  }


}
