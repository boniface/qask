import conf.CORSFilter
import play.api.{Application, GlobalSettings}
import play.api.libs.concurrent.Akka
import play.api.mvc.WithFilters
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Play.current

/**
 * Created by hashcode on 2014/07/19.
 */
object Global extends WithFilters(CORSFilter()) with GlobalSettings  {

  override def onStart(app: Application): Unit = {

    super.onStart(app)
    println("This is Not really Schewduling ")
    Akka.system.scheduler.
      scheduleOnce(1000.microsecond) {
      println("This is Executing ")
    }

  }
}
