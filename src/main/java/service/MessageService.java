package service;

import dao.DAOMessage;
import entity.Message;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

@AllArgsConstructor
public class MessageService {
    private final DAOMessage daoMessage;

    public void addMessage(Message message) {
        daoMessage.insert(message);
    }

    @SneakyThrows
    public List<Message> getAllMessages(int senderId, int receiverId) {
        return daoMessage.getAll(senderId, receiverId);
    }
}
