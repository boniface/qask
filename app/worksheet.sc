

import java.net.URL


import com.rometools.rome.io.{XmlReader, SyndFeedInput}


try {

val sfi = new SyndFeedInput()
val urls = List("https://www.facebook.com/feeds/page.php?format=rss20&id=1512926715599838")

urls.foreach(url => {
  val feed = sfi.build(new XmlReader(new URL(url)))

  val entries = feed.getEntries

  println(feed.getTitle)
  println(entries.size)
})
} catch {
  case e => throw new RuntimeException(e)
}

println("hello Worllo")

val r = List(1,2,3)
