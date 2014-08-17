package domain



import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/07.
 */
case class Role(
                 id: String,
                 rolename: String,
                 description: String
                 )

object Role {
  implicit val roleFmt = Json.format[Role]
}
