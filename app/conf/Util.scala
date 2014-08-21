package conf

/**
 * Created by hashcode on 2014/08/16.
 */
object Util extends Enumeration {

  import java.net._

  def md5Hash(text: String): String = {
    val hash = text + InetAddress.getLocalHost.getHostName
    java.security.MessageDigest.getInstance("MD5").digest(hash.getBytes()).map(0xFF & _).map {
      "%02x".format(_)
    }.foldLeft("") {
      _ + _
    }
  }

  val SPAM, PENDING, APPROVED, QUESTION,RESPONSE,FEED, POST = Value
}
