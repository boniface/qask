package controllers

import com.google.gson.Gson
import models.RoleModel
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

/**
 * Created by hashcode on 2014/06/18.
 */
@RunWith(classOf[JUnitRunner])
class PostTestForControllers extends Specification {
  val gson = new Gson()

  "Controllers" should {


    "Should Save Role Object" in new WithApplication {
      val role = RoleModel("NEW MODEL ", "Guest NEW MODEL")
      val jsonstring = gson.toJson(role).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        POST, "/api/role").withJsonBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")

    }



    //    "Should Delete" in new WithApplication {
    //      val Some(home)= route(FakeRequest(GET, "/api/role/delete/a4b522d8-62bf-45ef-b22f-cb0082158ec1"))
    //      status(home) must equalTo(OK)
    //
    //    }


  }

}