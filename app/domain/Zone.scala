package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/08/16.
 */
case class Zone(id:String,name:String,code:String,status:String, flag:String)

object Zone{
  implicit lazy val zoneFmt = Json.format[Zone]

}
