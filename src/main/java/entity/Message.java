package entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
public class Message {
    private final int sender_id;
    private final int receiver_id;
    private final String message;
    private LocalDateTime time;

}
