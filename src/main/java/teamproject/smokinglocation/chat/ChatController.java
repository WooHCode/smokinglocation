package teamproject.smokinglocation.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {

    private final SimpMessageSendingOperations template;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+ "님이 입장하셨습니다.");
        }
        log.info("{}의 message {}",message.getSender(),message.getMessage());
        log.info("roomId = {}",message.getRoomId());
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
