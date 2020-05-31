package dao;

import database.DBOperation;
import entity.Message;

import java.util.List;

public class DAOMessage implements DAO<Message> {

    @Override
    public Message get(int id) {
        return null;
    }

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public List<Message> getAll(int id1, int id2) {
        return DBOperation.getAllMessages(id1, id2);
    }

    @Override
    public void insert(Message t) {
        DBOperation.insertMessage(t);
    }

    @Override
    public List<Message> getAll(int id) {
        return null;
    }

    @Override
    public void update(Message t) {

    }
}
