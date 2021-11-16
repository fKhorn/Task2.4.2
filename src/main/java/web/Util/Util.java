package web.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Util {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    @Transactional
    public void setDefaultUsers() {
        Set<Role> roles = new HashSet<>();
        roleService.addRole(new Role("ROLE_USER"));
        roleService.addRole(new Role("ROLE_ADMIN"));
        roles.add(roleService.getRoleByName("ROLE_USER"));
        User user = new User("testUser", "testUser", "user", "user", roles);
        userService.setPasswordEncoder(user);
        userService.initialUser(user);
        roles.clear();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        User admin = new User("testAdmin", "testAdmin", "admin", "admin", roles);
        userService.setPasswordEncoder(admin);
        userService.initialUser(admin);
    }

}
