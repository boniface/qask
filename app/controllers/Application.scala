package controllers


import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  def options(path:String) = Action { Ok("")}

  def test(zone:String,id:String) = Action{
    request =>
      println("The ZONE IS ", zone)
      println("THE ID IS ", id)
      println("Output 3", request.id)
      println("Output4", request.uri)
      println("Output 5", request.remoteAddress)
      Ok("Hellow")
  }

}