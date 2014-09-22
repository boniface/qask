
import java.net.URL


import com.github.slugify.Slugify
import com.gravity.goose.{Configuration, Goose}
import com.redis._
import de.l3s.boilerpipe.extractors.{ArticleSentencesExtractor, ArticleExtractor}


val slug = new Slugify()
val seo = slug.slugify("This is a test Post and Lets See ")

val nestr = "This is a test String for the Systemm to just See the chara get".substring(0,10)

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
  l <-res.get
  ans =l.get
  rep = ans
} yield rep
val les = list.mkString(" ")
val set = Set("hello","lheLot","lusaka","lusakatime")
val str = set.mkString(" ").split(' ').map(_.capitalize).mkString(",")