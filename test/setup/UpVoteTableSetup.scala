package setup

import org.scalatest.{GivenWhenThen, FeatureSpec}
import respository._

/**
 * Created by hashcode on 2014/07/07.
 */

class UpVoteTableSetup extends FeatureSpec with GivenWhenThen {

  feature(" Set up UpVote Table") {
    info("As a Deployer")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the Cassandra")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val upvotetable = UpVoteRepository
      val questionsTable = QuestionRespository
      val answerTAble = ResponseRepository
      val commentsTable = CommentRepository
      When(" When Create method is called ")
      upvotetable.createTable()
      questionsTable.createTable()
      answerTAble.createTable()
      commentsTable.createTable()
      Then(" The The Table is Created")

    }
  }

}
