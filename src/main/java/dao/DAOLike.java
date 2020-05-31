package dao;

import database.DBOperation;
import entity.Like;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class DAOLike implements DAO<Like> {
    private List<Like> likes;

    @Override
    public Like get(int id) {
        return null;
    }

    @Override
    public List<Like> getAll() {
        this.likes = DBOperation.getLikes();
        return likes;
    }

    @Override
    public List<Like> getAll(int id1, int id2) {
        return null;
    }

    @Override
    public void insert(Like like) {
        DBOperation.insertLike(like);
    }

    @Override
    public List<Like> getAll(int id) {
        return null;
    }

    @Override
    public void update(Like t) {

    }
}
