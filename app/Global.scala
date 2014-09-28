import java.util.concurrent.TimeUnit

import akka.actor.Props
import conf.CORSFilter
import play.api.libs.concurrent.Akka
import play.api.libs.concurrent.Execution.Implicits._
import play.api.mvc.WithFilters
import play.api.{Application, GlobalSettings, Logger}
import services.actors.ContentProcessingActor

import services.messages.Messages._
import scala.concurrent.duration._

/**
 * Created by hashcode on 2014/07/19.
 */
object Global extends WithFilters(CORSFilter()) with GlobalSettings {

  override def onStart(app: Application): Unit = {

    super.onStart(app)
    schedular(app)
  }

  def schedular(app: Application) = {
    Logger.info("Starting The Daemon")
    val contentProcessingActor = Akka.system(app).actorOf(Props(new ContentProcessingActor()))
    Akka.system(app).scheduler.schedule(
        Duration.create(0, TimeUnit.MILLISECONDS), //Initial delay 0 milliseconds
        Duration.create(20, TimeUnit.MINUTES),     //Frequency 15 minutes
        contentProcessingActor,  Start("START"))
  }


}
