package teamproject.smokinglocation.chat;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatRoom {

    private String roomId;
    private String name;
    public static ChatRoom create(String name) {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.roomId = "1";
            chatRoom.name = name;
            return chatRoom;
    }
}
