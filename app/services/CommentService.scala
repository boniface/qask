package services

import domain.Comment
import respository.{CommentRepository, QuestionRespository}

/**
 * Created by hashcode on 2014/07/23.
 */
object CommentService {

  def save (comment:Comment) = {
    CommentRepository.save(comment)
  }
  def getCommentsByResponse(subjectId:String,zone:String) = {
    CommentRepository.getCommentsByResponseId(subjectId,zone)
  }
}
