package controllers


import play.api.libs.json.Json
import play.api.mvc._
import respository._
import scala.concurrent.ExecutionContext.Implicits.global

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
      println("Output 3", request.id)
      println("Output4", request.uri)
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
      ps <- PostStatusRepository.createTable()
      qr <- QuestionRespository.createTable()
      rp <- RawPostRespository.createTable()
      rr <- ResponseRepository.createTable()
      ro <- RoleRepository.createTable()
      si <- SiteRepository.createTable()
      uv <- UpVoteRepository.createTable()
      zr <- ZoneRespository.createTable()

    } yield (ab)
    results map (result => {
      Ok(Json.toJson("Done"))
    })


  }

}