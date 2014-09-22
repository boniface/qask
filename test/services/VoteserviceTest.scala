package services

import org.scalatest.{FeatureSpec, GivenWhenThen}

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

    }
  }
}
