import java.net.URL

import scala.xml._


def getRssFeeds(fileName: String): List[(String, Seq[String])] = {
  // Given a config file containing a list of rss feed URL's,
  // returns a list with ('channel name', ['article 1', 'article 2'])
  var baseList: List[(String, Seq[String])] = List()
  getFileLines(fileName).map(extractRss).foldLeft(baseList) { (l, r) => l ::: r.toList}
}

def combineFeeds(feeds: List[(String, Seq[String])])
: List[(String, Seq[String])] = {
  // Cleanup function, given a list of feeds it combines duplicate channels.
  def combinedFeed(feedName: String): Seq[String] = {
    feeds.filter(_._1 == feedName).map(_._2).flatten
  }

  val feedNames = feeds.map(_._1).distinct

  feedNames.map(x => (x, combinedFeed(x)))
}


def getFileLines(fileName: String): Array[String] =
// Extracts the URL's from a file and breaks them up into individual strings.
  scala.io.Source.fromFile(fileName).mkString.split("\n")

def extractRss(urlStr: String): Seq[(String, Seq[String])] = {
  // Given a URL, returns a Seq of RSS data in the form:
  // ("channel", ["article 1", "article 2"])
  val url = new URL(urlStr)
  val conn = url.openConnection
  val xml = XML.load(conn.getInputStream)

  for (channel <- xml \\ "channel")
  yield {
    val channelTitle = (channel \ "title").text
    val titles = extractRssTitles(channel)
    (channelTitle, titles)
  }
}

def extractRssTitles(channel: Node): Seq[String] = {
  // Given a channel node from an RSS feed, returns all of the article names
  for (title <- (channel \\ "item") \\ "title") yield title.text
}

def printFeeds(feeds: List[(String, Seq[String])]): List[Seq[Unit]] = {
  // Given a list of ("channel", ["article 1", "article 2"]), prints
  //  them each to screen.
  for (feed <- feeds) yield {
    println("*** " + feed._1 + " ***")
    for (title <- feed._2) yield println("\t" + title)
  }
}






