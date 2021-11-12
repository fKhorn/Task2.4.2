package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {
    void addRole(Role role);
    Role getRoleByName(String name);
    List<Role> getAllRoles();
}
