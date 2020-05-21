package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<A> {
    A get(int id) throws SQLException;

    List<A> getAll() throws SQLException;

    List<A> getAll(int id1, int id2) throws SQLException;

    void insert(A t);

    List<A> getAll(int id) throws SQLException;

    void update(A t);
}
