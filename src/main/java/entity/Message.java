package entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@RequiredArgsConstructor
public class Message {

    private int sender;
    private int receiver;
    private String content;
    private long date;
    private String stringDate;

    public Message(int from, int to, String content) {
        this.sender = from;
        this.receiver = to;
        this.content = content;
    }
}
