package controllers

import controllers.RoleController._
import domain.{DownVote, UpVote}
import models.VoteResultsModel
import play.api.libs.json.Json
import play.api.mvc.Action
import services.VoteService

import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Created by hashcode on 2014/07/08.
 */
object VoteController {

  def upvote(subjectId: String) = Action.async {
    request =>
      val vote = UpVote(subjectId, "User1", "YES")
      VoteService.castUpVote(vote)
      val results = for {
        downvotes <- VoteService.getDownVotes(subjectId)
        upvotes <- VoteService.getUpVotes(subjectId)
      } yield VoteResultsModel(upvotes.size, downvotes.size)
      results map (result => {
        Ok(Json.toJson(result))
      })
  }

  def downvote(subjectId: String) = Action.async {
    request =>
      val vote = DownVote(subjectId, "User1", "NO")
      VoteService.castDownVote(vote)
      val results = for {
        downvotes <- VoteService.getDownVotes(subjectId)
        upvotes <- VoteService.getUpVotes(subjectId)
      } yield VoteResultsModel(upvotes.size, downvotes.size)
      results map (result => {
        Ok(Json.toJson(result))
      })
  }

  def votes(subjectId: String) = Action.async {
    request =>
      val results = for {
        downvotes <- VoteService.getDownVotes(subjectId)
        upvotes <- VoteService.getUpVotes(subjectId)
      } yield VoteResultsModel(upvotes.size, downvotes.size)
      results map (result => {
        Ok(Json.toJson(result))
      })
  }
}
