package models

import java.util.UUID

import domain.Role
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/06/19.
 */
case class RoleModel(role: String, description: String) {
  def getDomain(): Role = RoleModel.domain(this)
}

object RoleModel {
  implicit val roleFmt = Json.format[RoleModel]

  def domain(model: RoleModel) = {
    Role(UUID.randomUUID(), model.role, model.description)
  }
}