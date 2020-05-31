package dao;

import database.DBOperation;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class DAOUser implements DAO<User> {
    private List<User> users;

    public List<User> getUsersToDisplay(int id) {
        try {
            return DBOperation.getUsersToDisplay(id);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public User get(int id) {
        return DBOperation.getUser(id);
    }

    @Override
    public List<User> getAll() {
        try {
            this.users = DBOperation.getAllUsers();
        } catch (Exception ignored) {
        }
        return users;
    }

    @Override
    public List<User> getAll(int id1, int id2) {
        return null;
    }

    @Override
    public void insert(User user) {
        DBOperation.insertUser(user);
    }

    @Override
    public List<User> getAll(int id) {
        List<User> likedUsers;
        likedUsers = DBOperation.getLikedUsers(id);
        return likedUsers;
    }

    @Override
    public void update(User t) {
        DBOperation.updateLastSeen(t.getId());
    }
}
