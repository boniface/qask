package services.customfetch.qfm

import de.l3s.boilerpipe.extractors.ArticleExtractor

import scala.util.{Failure, Success, Try}

/**
 * Created by hashcode on 2014/10/19.
 */
class FetchQfmContent {
  def getContent(url:String)={
    Try(ArticleExtractor.INSTANCE.getText(url)) match {
      case Success(text) => text
      case Failure(e) => "none"
    }
  }
}
