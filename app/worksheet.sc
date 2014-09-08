import java.net.URL

import scala.xml._



val url = new URL("https://www.facebook.com/feeds/page.php?format=rss20&id=129987587052000")
val conn = url.openConnection
val xml = XML.load(conn.getInputStream)

val result =for (channel <- xml \\ "channel")
yield {
  val channelTitle = (channel \ "title").text
  channelTitle
}

print("the results is ",result)


