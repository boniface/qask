package respository
import com.redis._

/**
 * Created by hashcode on 2014/09/17.
 */
class FilterExpressionRepository {

  val client = new RedisClient("localhost", 6379)

  def addFilterExpression(expression:String,set:String) ={
      client.sadd(set,expression)
  }
  def deleteExpression (set:String,expression:String)={
    client.srem(set,expression)
  }

  def getExpressions(set:String)={
    client.smembers(set)
  }

  def getFilterExperession(set:String,expression:String) ={
    client.sinter(set,expression)
  }
}
