package teamproject.smokinglocation.chat;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomId;

    private String name;


    public static ChatRoom create(String name) {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.roomId = UUID.randomUUID().toString();
            chatRoom.name = name;
            return chatRoom;
    }
}
