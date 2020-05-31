package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {

    private int sender;
    private int receiver;
    private String content;
    private String date;

    public Message(int sender, int receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}
