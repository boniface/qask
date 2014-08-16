package services

import domain.{Stats, Response}
import respository.{StatsRepository, ResponseRepository}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by hashcode on 2014/07/23.
 */
class AnswerService {
  val repo = ResponseRepository
  val statsRepo = StatsRepository



  def save(answer: Response) = {
    repo.save(answer)
  }

  def getAnswersByQuestion(questionId: String) = {
    repo.getAnswersByQuestionId(questionId)
  }

  def countStat(stat:Stats)={
    statsRepo.statcount(stat)
  }



}
