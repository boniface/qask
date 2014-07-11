package services

import domain.UpVote


/**
 * Created by hashcode on 2014/07/08.
 */
class VoteserviceTest extends FeatureSpec with GivenWhenThen {
  feature(" Set up UpVote Table") {
    info("As a Deployer")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the Cassandra")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val upvoteService = new VoteService
      When(" When Create method is called ")
      val vote1 = UpVote("Subject1", "User1", "NOYES")
      upvoteService.castUpVote(vote1)
      Then(" The The Table is Created")

    }
  }

}
