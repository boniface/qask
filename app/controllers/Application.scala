package controllers


import java.io.File
import java.net.URL
import scala.collection.JavaConverters._

import com.rometools.rome.io.{XmlReader, SyndFeedInput}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import respository._
import services.actors.SocialMediaActor
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.Play.current


object Application extends Controller {

  def index = Action {



    Ok(views.html.index("Your new application is ready."))
  }

  def options(path: String) = Action {
    Ok("")
  }

  def test(zone: String, id: String) = Action {
    request =>
      println("The ZONE IS ", zone)
      println("THE ID IS ", id)

      println("Output 5", request.remoteAddress)
      Ok("Testing Params")

  }

  def dbsetup = Action.async {
    val results = for {
      ab <- AbuseReportRespository.createTable()
      com <-CommentRepository.createTable()
      dv <- DownVoteRepository.createTable()
      fr <- FeedsRespository.createTable()
      lr <- LinksRespository.createTable()
      pr <- PostRespository.createTable()
      ps <- StatusRepository.createTable()
      qr <- QuestionRespository.createTable()
      rp <- RawPostRespository.createTable()
      rr <- ResponseRepository.createTable()
      ro <- RoleRepository.createTable()
      si <- SiteRepository.createTable()
      uv <- UpVoteRepository.createTable()
      zr <- ZoneRespository.createTable()
      st <-StatsRepository.createTable()
      zp <-ZonePostRespository.createTable()
      sp <-SitePostRespository.createTable()
      smf <-SocialMediaFeedsRespository.createTable()
      clk <-CustomLinkRepository.createTable()
      cfr <-CustomFeedRepository.createTable()
      cpl <-CustomProcessedLinkskRepository.createTable()
    } yield (ab)
    results map (result => {
      Ok(Json.toJson("Done"))
    })
  }

  def upload = Action(parse.temporaryFile) { request =>
//    val file = request.body
//    val j= Json.fromJson(file)
    println("The Uploaded File is ", request.body.file)
    val fi = request.body.file

    print(fi.getTotalSpace)

//    request.body.moveTo(new File("images/"))

    Ok("File uploaded")
  }

  def socket = WebSocket.tryAcceptWithActor[String, String] { request =>
    Future.successful(request.session.get("user") match {
      case None => Left(Forbidden)
      case Some(_) => Left(Forbidden)
    })
  }

}