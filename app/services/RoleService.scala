package services

import java.util.UUID

import domain.Role
import respository.RoleRepository

/**
 * Created by hashcode on 2014/06/18.
 */
class RoleService {

  def save(role: Role) = {
    RoleRepository.save(role)
  }

  def findAll() = {
    RoleRepository.getAllRoles
  }

  def findById(roleid: UUID) = {
    RoleRepository.getRoleById(roleid)
  }

  def updateRoleName(roleid: UUID, rolename: String) = {
    RoleRepository.updateRoleName(roleid, rolename)
  }

  def updateDescription(roleid: UUID, description: String) = {
    RoleRepository.updateRoleDescription(roleid, description)
  }

  def deleteRole(roleId: UUID) = {
    RoleRepository.deleteRoleById(roleId)
  }

}
