package respository

import domain.Stats
import org.scalatest.{GivenWhenThen, FeatureSpec}
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
 * Created by hashcode on 2014/08/14.
 */
class ViewsTest  extends FeatureSpec with GivenWhenThen {
  feature ("Count Views"){
    info(" When The Count is Issued")
    scenario(" "){
      val view = Stats("2",1)

      val rep = StatsRepository
      rep.statcount(view)

      val views = Await.result(rep.getStats("2"), 500 millis)

      views map(view => println("The Views are ", view.counter))




    }

  }

}
