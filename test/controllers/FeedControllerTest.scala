package controllers

import com.google.gson.Gson
import domain.Feed
import models.FeedsModel
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

/**
 * Created by hashcode on 2014/08/21.
 */
@RunWith(classOf[JUnitRunner])
class FeedControllerTest extends Specification {
  val gson = new Gson()

  "Controllers" should {

    "Should Save Feed Object" in new WithApplication {
      val feed = FeedsModel("http://www.lusakatimes.com/feed/","rss","lusakatimes.com","var/images.jpg")
      val jsonstring = gson.toJson(feed).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        POST, "/api/feed/create/ZM").withJsonBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Save Feed Object" in new WithApplication {
      val feed = FeedsModel("http://zambiadailynation.com/feed/","rss","zambiadailynation.com","var/images.jpg")
      val jsonstring = gson.toJson(feed).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        POST,  "/api/feed/create/ZM").withJsonBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }
  }

}
