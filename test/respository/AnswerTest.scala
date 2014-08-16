package respository

import domain.{Comment, Response}
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
 * Created by hashcode on 2014/07/27.
 */
class AnswerTest extends FeatureSpec with GivenWhenThen {

  feature(" Add Records to the Respository"){

    info(" As a User I want to Add a Record to the Database")
    info(" So that I can see the result in the DB")
    info("")
    scenario("Create Tables in The Database "){

      Given(" The Stab to the Database")
      val repo = ResponseRepository
      val comment = Comment("100","1",new DateTime,"This is a Coment","bojovi@jkd.com","KAYADS","123.4.5.67")
      val answer = Response("123","102",new DateTime,"The Response is very Long I think ","bsadas@kabs","chanda","123.4",Set[Comment]())
//      repo.save(answer)
//      repo.postComment("123","100",comment)

      val answers = repo.getAnswersByQuestionId("27090ba50639e5a6fc127140cac9fd78")

      val ans = Await.result(repo.getAnswersByQuestionId("27090ba50639e5a6fc127140cac9fd78"), 5000 millis)




         for (a<-ans){
           println(a.comments)
         }

    }

  }

}