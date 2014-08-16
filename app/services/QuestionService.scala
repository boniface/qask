package services

import java.util.{Date, UUID}

import domain.{Stats, Question}
import org.joda.time.DateTime
import respository.{ResponseRepository, StatsRepository, QuestionRespository}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/17.
 */
class QuestionService {
  val repo = QuestionRespository
  val statsRepo = StatsRepository
  val answerrepo = ResponseRepository

//  def getQuestionBeforedate(date:Date) ={
//    repo.geAllQuestions map( questions=> questions.filter(_.date.before(date)))
//  }

  def save(question:Question) = {
    repo.save(question)
  }

  def getQuestionById(id:String) ={
    repo.getRoleById(id)
  }

  def getAllQuestion = {
    repo.geAllQuestions
  }

  def countStat(view:Stats) = {
    statsRepo.statcount(view)

  }

  def getStats(id:String,item:String) = {
    statsRepo.getStats(id,item)
  }



}
