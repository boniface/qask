package models

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/09/17.
 */
case class FilterExpressionModel(expression:String)

object FilterExpressionModel {
  implicit val exprFmt = Json.format[FilterExpressionModel]
}
