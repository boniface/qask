package services

import com.github.slugify.Slugify
import com.gravity.goose.{Configuration, Goose}
import domain.{Link, Post}

/**
 * Created by hashcode on 2014/09/21.
 */
object FetchContent {
  def getContent(link:Link):Post = {
    val article = new Goose(new Configuration).extractContent(link.url)
   Post(
      link.zone,
      link.linkhash,
      article.getDomain(),
      link.datePublished,
      article.getTitle(),
      article.getCleanedArticleText(),
      getMetaKeywords(article.title),
      getMedecription(article.getCleanedArticleText),
      article.getCanonicalLink,
      article.getTopImage().getImageSrc,
      getPrettySeo(article.title),
      article.getTopImage().getImageSrc,
      getCaption(),
      link.siteCode
   )
  }
  def getMetaKeywords(title:String)={
    val cleanedWords = FilterService.removeStopWords(title)
    cleanedWords.split(' ').map(_.capitalize).mkString(",")
  }

  def getMedecription(article:String)={
    val description = article.substring(0,156)
    description.split(' ').map(_.capitalize).mkString(" ")
  }
  def getPrettySeo(title:String)={
    val cleanedWords = FilterService.removeStopWords(title)
    val slg = new Slugify()
     slg.slugify(cleanedWords)
  }

  def getCaption() = {
    "NoCaption"
  }

  def getMovies = {

  }
}
