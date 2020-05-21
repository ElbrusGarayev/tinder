package entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
public class Message {
    private int id;
    private int sender;
    private int receiver;
    private String content;
    private LocalDateTime time;

    public Message(int id, int from, int to, String content) {
        this.id = id;
        this.sender = from;
        this.receiver = to;
        this.content = content;
    }

    public Message(int sender, int receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}
