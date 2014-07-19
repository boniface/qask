package setup

import org.scalatest.{GivenWhenThen, FeatureSpec}
import respository.RoleRepository

/**
 * Created by hashcode on 2014/07/07.
 */

class RoleTableSetup extends FeatureSpec with GivenWhenThen {
  feature(" Set up Role Table") {
    info("As a Deployer")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the Cassandra")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val roletable = RoleRepository
      When(" When Create method is called ")
      roletable.createTable()
      Then(" The The Table is Created")

    }
  }

}
