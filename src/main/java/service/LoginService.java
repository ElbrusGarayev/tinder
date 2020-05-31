package service;

import dao.DAOUser;
import entity.User;
import lombok.SneakyThrows;

import java.util.List;

public class LoginService {
    private final DAOUser daoUser;

    public LoginService(DAOUser daoUser) {
        this.daoUser = daoUser;
    }

    @SneakyThrows
    public boolean isUserValid(String email, String password) {
        List<User> users = daoUser.getAll();
        return users.stream().anyMatch(user -> user.getPassword().equals(password) && user.getEmail().equals(email));
    }

    @SneakyThrows
    public User getUser(String email, String password) {
        List<User> users = daoUser.getAll();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void updateLastSeen(User user) {
        daoUser.update(user);
    }
}
