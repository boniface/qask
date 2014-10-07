package controllers


import java.io.File
import java.net.URL

import com.rometools.rome.io.{XmlReader, SyndFeedInput}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import respository._
import services.actors.ContentDisplayActor
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.Play.current


object Application extends Controller {

  def index = Action {

    val sfi = new SyndFeedInput()
    val urls = List("https://www.facebook.com/feeds/page.php?format=rss20&id=1512926715599838")

    urls.foreach(url => {
      val feed = sfi.build(new XmlReader(new URL(url)))

      val entries = feed.getEntries

      println(feed.getTitle)
      println(feed.getLink)
      println(entries.get(0).getLink)
      println(entries.get(0).getContents)
      println(entries.get(0).getDescription)
      println(entries.get(0).getPublishedDate)
      println(entries.get(0).getTitle)
      println(entries.get(0).getUri)
      println(entries.get(0).getForeignMarkup)
      println(entries.size)
    })

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
      case Some(_) => Right(ContentDisplayActor.props)
    })
  }

}