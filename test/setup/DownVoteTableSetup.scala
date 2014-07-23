package setup

import org.scalatest.{GivenWhenThen, FeatureSpec}
import respository.DownVoteRepository

/**
 * Created by hashcode on 2014/07/07.
 */

class DownVoteTableSetup extends FeatureSpec with GivenWhenThen {

  feature(" Set up DownVote Table") {
    info("As a Deployer")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the Cassandra")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val downvotetable = DownVoteRepository

      When(" When Create method is called ")
      downvotetable.createTable()
      Then(" The The Table is Created")

    }
  }

}
