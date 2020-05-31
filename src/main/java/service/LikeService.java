package service;

import dao.DAOUser;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

@AllArgsConstructor
public class LikeService {
    private final DAOUser daoUser;

    @SneakyThrows
    public List<User> getLikedUsers(int userId) {
        return daoUser.getAll(userId);
    }
}
