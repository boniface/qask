package services

import java.util.{Date, UUID}

import domain.{Stats, Question}
import org.joda.time.DateTime
import respository.{ResponseRepository, StatsRepository, QuestionRespository}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/17.
 */
object QuestionService {

//  def getQuestionBeforedate(date:Date) ={
//    repo.geAllQuestions map( questions=> questions.filter(_.date.before(date)))
//  }

  def save(question:Question) = {
    QuestionRespository.save(question)
  }

  def getQuestionById(zone:String, id:String) ={
    QuestionRespository.getQuestionById(zone,id)
  }

  def getQuestionsByZone (zone:String) = {
    QuestionRespository.geQuestionsByZone(zone)
  }

  def countStat(view:Stats) = {
    StatsRepository.statcount(view)

  }

  def getStats(id:String,item:String) = {
    StatsRepository.getStats(id,item)
  }



}
