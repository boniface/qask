package models

import java.util.UUID

import conf.Util
import domain.Zone
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/08/28.
 */
case class ZoneModel( name: String,
                      code: String,
                      flag: String) {
  def getDomain(): Zone = ZoneModel.domain(this)
}

object ZoneModel {
  implicit val zoneFmt = Json.format[ZoneModel]

  def domain(model: ZoneModel) = {
    Zone(Util.md5Hash(UUID.randomUUID().toString()), model.name, model.code, Util.ENABLED.toString, model.flag)
  }
}