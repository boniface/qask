package services

import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
 * Created by hashcode on 2014/09/22.
 */
class FilterTest extends FeatureSpec with GivenWhenThen {
  feature(" Set up UpVote Table") {
    info("As a Deployer")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the Cassandra")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
//      val links = LinksService.getLatestLinks("ZM")
//      links map (link => link foreach (l => println(" The links is ", l.url)))

      val links = Await.result(LinksService.getLatestLinks("ZM"), 5000 millis)
      links.foreach (link => {
        println("This Works ", link.url)
        val post = FetchContent.getContent(link)
        PostsService.create(post)
        println("Post Submitted  ", link.url)
      }
        )
    }
  }
}
