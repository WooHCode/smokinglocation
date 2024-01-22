package teamproject.smokinglocation.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamproject.smokinglocation.service.MemberService;
import teamproject.smokinglocation.userEnitiy.Member;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberService memberService;

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomRepository.findAll();
    }
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public String createRoom(@RequestParam String name, @RequestParam String rf) {
        Long memberIdByRefreshToken = memberService.getMemberIdByRefreshToken(rf);
        Member byId = memberService.findById(memberIdByRefreshToken);
        String memberName = byId.getMemberName();
        Optional<ChatRoom> roomByMemberName = chatRoomRepository.findRoomByMemberName(memberName);
        if (roomByMemberName.isPresent() && !roomByMemberName.get().getRoomId().isEmpty()) {
            return roomByMemberName.get().getRoomId();
        } else {
            ChatRoom chatRoom = ChatRoom.create(name);
            ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
            return savedChatRoom.getRoomId();
        }
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        System.out.println("roomId = " + roomId);
        model.addAttribute("roomId", roomId);
        return "chat/roomdetail";
    }


    // 관리자 채팅방 입장 화면
    @GetMapping("/room/enter/manager")
    public String roomDetailM(Model model, @RequestParam String roomId) {
        System.out.println("roomId for manager = " + roomId);
        model.addAttribute("roomId", roomId);
        return "chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

    @PostMapping("/room/clear")
    @ResponseBody
    public ResponseEntity deleteRoom(@RequestParam String roomId) {
        ChatRoom roomById = chatRoomRepository.findRoomById(roomId);
        chatRoomRepository.delete(roomById);
        return new ResponseEntity(HttpStatus.OK);
    }
}
