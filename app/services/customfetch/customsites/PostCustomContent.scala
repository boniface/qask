package services.customfetch.customsites

import com.gravity.goose.Article
import conf.Util
import domain.{CustomLink, Post}
import services.{PostsService, FetchContent}

/**
 * Created by hashcode on 2014/10/19.
 */
object PostCustomContent {

  private def createPost(article: Article, meta: CustomLink) = {
    Post(
      meta.zone,
      Util.md5Hash(meta.url),
      article.getDomain(),
      meta.datePublished,
      meta.title,
      article.getCleanedArticleText(),
      FetchContent.getMetaKeywords(meta.title),
      FetchContent.getMedecription(article.getCleanedArticleText),
      article.getCanonicalLink,
      article.getTopImage().getImageSrc,
      FetchContent.getPrettySeo(meta.title),
      article.getTopImage().getImageSrc,
      FetchContent.getCaption(),
      meta.siteCode
    )
  }

  def postContent(link:CustomLink)={
    val article = FetchCustomContent.getContent(link.url)
    val post = createPost(article,link)
    PostsService.create(post)
  }

}
