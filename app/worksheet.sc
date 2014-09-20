
import java.net.URL


import com.gravity.goose.{Configuration, Goose}
import com.redis._
import de.l3s.boilerpipe.extractors.{ArticleSentencesExtractor, ArticleExtractor}


val route = new URL("https://www.zambianwatchdog.com/no-journalist-during-opening-of-parliament/");

//val  text = ArticleSentencesExtractor.INSTANCE.getText(route);

val goose = new Goose(new Configuration)
val article = goose.extractContent("http://www.daily-mail.co.zm/?p=4396")
article
println(" The Title",article.title)
println("The Article",article.cleanedArticleText)
println("The Date",article.getPublishDate())
println("the Image ", article.getTopImage.getImageSrc)
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
val set = Set("hello","hejjsje")
val str = set.mkString(" ")