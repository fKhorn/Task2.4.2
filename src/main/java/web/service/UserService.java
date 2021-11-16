package web.service;

import org.springframework.stereotype.Service;
import web.model.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    void addUser(User user, String[] checkboxroles);
    User getUserById(long id);
    void updateUser(User user, String[] checkboxroles);
    void deleteUser(long id);
    void initialUser(User user);
    void setPasswordEncoder(User user);
    User getUserByName(String name);
}