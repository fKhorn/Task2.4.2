package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void addUser(User user, String[] checkBoxRoles) {
        setPasswordEncoder(user);
        Set<Role> roles = new HashSet<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userDao.addUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user, String[] checkBoxRoles) {
        setPasswordEncoder(user);
        Set<Role> roles = new HashSet<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void initialUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void setPasswordEncoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}