
import com.redis._

val r = new RedisClient("localhost", 6379)
val mesg = "This is The head  for and The We need to Sort"

val ms1="This is another Headline"
val setB = ms1.split(" ").toSet

val setA = mesg.split(" ").toSet

val inter = setA.intersect(setB)
val union = setA.diff(setB)

setA foreach(e => r.sadd("title",e))

val res = r.sinter("title","stopwords")

val list = for {
  l <-res
  
} yield l
