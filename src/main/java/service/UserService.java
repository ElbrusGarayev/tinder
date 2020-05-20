package service;

import dao.DAOLike;
import dao.DAOUser;
import entity.Like;
import entity.User;

import java.util.List;

public class UserService {
    private final DAOUser daoUser;
    private final DAOLike daoLike;

    public UserService(DAOUser daoUser, DAOLike daoLike) {
        this.daoUser = daoUser;
        this.daoLike = daoLike;
    }

    public User getUserToDisplay(User curr) {
        List<User> likedUsers = daoUser.getUsersToDisplay(curr.getId());
        if (likedUsers.size() > 0) {
            return likedUsers.get(0);
        }
        return null;
    }

    public void addLike(Like like) {
        daoLike.insert(like);
    }

}
