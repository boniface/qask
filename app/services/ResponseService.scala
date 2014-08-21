package services

import domain.{Stats, Response}
import respository.{StatsRepository, ResponseRepository}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by hashcode on 2014/07/23.
 */
object ResponseService {
  def save(response: Response) = {
    ResponseRepository.save(response)
  }

  def getResponseById(subjectId: String, zone:String) = {
    ResponseRepository.getResponseBySubjectId(subjectId,zone)
  }

  def updateResponse(subjectId:String, response:String) = {
    ResponseRepository.updateResponse(subjectId, response)
  }

  def countStat(stat:Stats)={
    StatsRepository.statIncrement(stat)
  }
  
  def getAPrrovedResponse(subjectId: String, zone:String) = {
    getResponseById(subjectId,zone)


  }

  def getPendingResponses(subjectId: String, zone:String)= {
    getResponseById(subjectId,zone)
  }

  def getSpamResponses(subjectId: String, zone:String)= {
    getResponseById(subjectId,zone)
  }

  def deleteResponse(subjectId: String)= {
    ResponseRepository.deleteResponse(subjectId)
  }

  def changeResponseStatus(subjectId: String, status:String)= {


  }


}
