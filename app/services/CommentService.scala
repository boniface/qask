package services

import domain.Comment
import respository.{CommentRepository}
import com.redis._


/**
 * Created by hashcode on 2014/07/23.
 */
object CommentService {
  val r = new RedisClient("localhost", 6379)



  def save (comment:Comment) = {
    CommentRepository.save(comment)
  }
  def getCommentsByResponse(subjectId:String,zone:String) = {
    CommentRepository.getCommentsByResponseId(subjectId,zone)
  }
}
