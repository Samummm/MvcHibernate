package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public User getUserById(Integer id);

    public void updateUser(User user);

    public void deleteUser(Integer id);
    public List<User> getAllUsers();
}
