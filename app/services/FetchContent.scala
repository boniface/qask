package services

import com.gravity.goose.{Configuration, Goose}
import domain.{Link, Post}

/**
 * Created by hashcode on 2014/09/21.
 */
object FetchContent {
  def getContent(link:Link) = {
    val goose = new Goose(new Configuration)
    val article = goose.extractContent(link.url)
    val post = Post(
      link.zone,
      link.linkhash,
      link.site,
      link.datePublished,
      article.getTitle(),
      article.getCleanedArticleText(),
      article.metaKeywords,
      article.getMetaDescription,
      article.getFinalUrl,article.getTopImage().getImageSrc,
      "seo",
      article.getTopImage().getImageSrc,
      "caption",
      link.siteCode)
  }

  def getMetaKeywords(title:String)={

  }

  def getMedecription(title:String)={

  }

  def getPrettySeo(title:String)={

  }

  def getCaption() = {

  }

  def getMovies = {
    
  }

}
