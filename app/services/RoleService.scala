package services



import domain.Role
import respository.RoleRepository


/**
 * Created by hashcode on 2014/06/18.
 */
object RoleService {

  def save(role: Role) = {
    RoleRepository.save(role)
  }

  def findAll() = {
    RoleRepository.getAllRoles
  }

  def findById(roleid: String) = {
    RoleRepository.getRoleById(roleid)
  }

  def updateRoleName(roleid: String, rolename: String) = {
    RoleRepository.updateRoleName(roleid, rolename)
  }

  def updateDescription(roleid: String, description: String) = {
    RoleRepository.updateRoleDescription(roleid, description)
  }

  def deleteRole(roleId: String) = {
    RoleRepository.deleteRoleById(roleId)
  }

}
