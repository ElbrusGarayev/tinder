package dao;

import database.DBOperation;
import entity.Like;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class DAOLike implements DAO<Like>{
    private List<Like> likes;

    @Override
    public Like get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Like> getAll() throws SQLException {
        this.likes = DBOperation.getLikes();
        return likes;
    }

    @Override
    public void insert(Like like) {
        DBOperation.insertLike(like);
    }

    @Override
    public List<Like> getAll(int id) throws SQLException {
        return null;
    }

    @Override
    public void update(Like t) {

    }
}
