package respository

import org.scalatest.{GivenWhenThen, FeatureSpec}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._


/**
 * Created by hashcode on 2014/09/20.
 */
class LinksTest extends FeatureSpec with GivenWhenThen {
  feature ("Count Views"){
    info(" When The Count is Issued")
    scenario(" "){
      Given(" The Link ")

      val link = LinksRespository.getLatestLinks("ZM")

      link map ( links => println("The Size of The Links",links.size))

    }

  }

}
