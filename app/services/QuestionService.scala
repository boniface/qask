package services

import java.util.{Date, UUID}

import domain.Question
import org.joda.time.DateTime
import respository.QuestionRespository
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/17.
 */
class QuestionService {
  val repo = QuestionRespository

  def getQuestionBeforedate(date:Date) ={
    repo.geAllQuestions map( questions=> questions.filter(_.date.before(date)))
  }

  def save(question:Question) = {
    repo.save(question)
  }

  def getQUestionById(id:String) ={
    repo.getRoleById(id)
  }

  def getAllQuestion = {
    repo.geAllQuestions

  }

}
