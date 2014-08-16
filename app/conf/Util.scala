package conf

/**
 * Created by hashcode on 2014/08/16.
 */
object Util {

  def md5Hash(text: String): String = {
    java.security.MessageDigest.getInstance("MD5").digest(text.getBytes()).map(0xFF & _).map {
      "%02x".format(_)
    }.foldLeft("") {
      _ + _
    }
  }
}
