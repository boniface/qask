package services

import domain.Answer
import respository.AnswerRepository

/**
 * Created by hashcode on 2014/07/23.
 */
class AnswerService {
  val repo = AnswerRepository

  def save(answer: Answer) = {
    repo.save(answer)
  }

  def getAnswers(questionId: String) = {
    repo.getAnswersByQuestionId(questionId)
  }

}
