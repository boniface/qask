package conf

import play.api.libs.concurrent.Akka
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Play.current
import scala.concurrent.duration._

/**
 * Created by hashcode on 2014/09/14.
 */
object Tasks {
  Akka.system.scheduler.scheduleOnce(1000.microsecond) {
    println("This is Executing ")
  }

}
