package services

import domain.Comment
import respository.{CommentRepository, QuestionRespository}

/**
 * Created by hashcode on 2014/07/23.
 */
class CommentService {
  val repo = CommentRepository

  def save (comment:Comment) = {
    repo.save(comment)
  }

  def getComments(answerId:String) = {
    repo.getCommentsByAnswerId(answerId)
  }

}
