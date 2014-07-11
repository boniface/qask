package models

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/08.
 */
case class VoteResultsModel(up: Int, down: Int)

object VoteResultsModel {
  implicit val resultFmt = Json.format[VoteResultsModel]

}
