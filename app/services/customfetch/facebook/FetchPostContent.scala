package services.customfetch.facebook

import com.gravity.goose.{Article, Configuration, Goose}

import scala.util.{Failure, Success, Try}

/**
 * Created by hashcode on 2014/10/19.
 */
object FetchPostContent {
  def getContent(url:String):Article = {
    Try(new Goose(new Configuration).extractContent(url)) match {
      case Success(article) =>article
      case Failure(e) =>new Article
    }
  }


}
