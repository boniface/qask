package domain

import org.joda.time.DateTime
import play.api.libs.json.Json
import respository.CommentRepository

/**
 * Created by hashcode on 2014/07/09.
 */
case class Answer(
                   questionId: String,
                   id: String,
                   date: DateTime,
                   answer: String,
                   email: String,
                   screenName: String,
                   ipaddress: String,
                   comments: Seq[Comment]= Seq[Comment]()) {
  def getComments = Answer.getComments(this)

}

object Answer {
  implicit val answerFmt = Json.format[Answer]

  def getComments(answer: Answer) = {
    CommentRepository.getCommentsByAnswerId(answer.id)
  }

}
