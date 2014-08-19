package respository

import java.util.Date

import domain.Question

import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by hashcode on 2014/08/19.
 */
class QuestionRespositoryTest  extends FeatureSpec with GivenWhenThen {

  feature(" Add Questions to the Database"){
    info("As a User ..")
    scenario(" Given These Objects  "){
      val quest1 = Question("12","zm",new Date,"This is Question 1","This is a Question we need to Ask","k@k.com","Test")
      val quest2 = Question("13","za",new Date,"This is Question 1","This is a Question we need to Ask","k@k.com","Test")
      val quest3 = Question("15","ke",new Date,"This is Question 1","This is a Question we need to Ask","k@k.com","Test")
      val quest4 = Question("15","zm",new Date,"This is Question 1","This is a Question we need to Ask","k@k.com","Test")
      val quest5 = Question("16","za",new Date,"This is Question 1","This is a Question we need to Ask","k@k.com","Test")
      val quest6 = Question("17","zm",new Date,"This is Question 1","This is a Question we need to Ask","k@k.com","Test")

      QuestionRespository.save(quest1)
      QuestionRespository.save(quest2)
      QuestionRespository.save(quest3)
      QuestionRespository.save(quest4)
      QuestionRespository.save(quest5)
      QuestionRespository.save(quest6)

    }
  }

}
