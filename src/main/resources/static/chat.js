var roomId = localStorage.getItem("rmId");
var username = localStorage.getItem("temp");
let sockJs = null
let stomp = null
function addEnterKeyListener() {
    document.getElementById("message").addEventListener("keydown", function(e) {
        if (e.key === "Enter" && !e.shiftKey) { // Shift+Enter는 줄바꿈, Enter만 누를 경우 전송
            e.preventDefault(); // Enter 키에 의한 줄바꿈 방지
            sendingMessage(); // 메시지 전송 함수 호출
        }
    });
}
function sendingButtonLoad() {
    var sendingButton = document.getElementById("button-send")
    sendingButton.style.display = "block"
}
function connectionButtonHide() {
    var connectionButton = document.getElementById("startChat")
    connectionButton.style.display = "none"
}
function onConnected() {
    sendingButtonLoad()
    connectionButtonHide()
    stomp.subscribe("/sub/chat/room/" + roomId, function (chat) {
        var content = JSON.parse(chat.body);
        console.log("receive message = " + content.message)
        var sender = content.sender;
        var str = '';
        if (sender === username) {
            str = "<div class='message user'>";
            str += "<b>" + sender + " : " + content.message + "</b>"; // 새로운 div 태그 추가
            str += "</div>";
            $("#chatWindow").append(str);
        } else {
            str = "<div class='message admin'>";
            str += "<b>" + sender + " : " + content.message + "</b>"; // 새로운 div 태그 추가
            str += "</div>";
            $("#chatWindow").append(str);
        }
        // 스크롤을 최하단으로 이동시키는 로직
        var chatWindow = document.getElementById("chatWindow");
        // 스크롤이 최하단에 있는지 확인
        if (chatWindow.scrollTop + chatWindow.clientHeight >= chatWindow.scrollHeight - 5) {
            // 스크롤바 보이기
            chatWindow.style.overflow = 'auto';
        }
        // 새로운 메시지가 추가된 후 스크롤을 최하단으로 이동
        chatWindow.scrollTop = chatWindow.scrollHeight;

    });
    stomp.send('/pub/chat/message', {}, JSON.stringify({type: "ENTER", roomId: roomId, sender: username}))
}
function afterChatPopupLoaded () {
    sockJs = new SockJS("/stomp/chat");
//1. SockJS를 내부에 들고있는 stomp를 내어줌
    stomp = Stomp.over(sockJs);
    console.log(roomId + ", " + username);
    stomp.connect({}, onConnected, function (error) {
        console.log("stomp error: "+error)
    });
    addEnterKeyListener();
}


function sendingMessage() {
        var msg = document.getElementById("message");
        console.log(username + ":" + msg.value);
        stomp.send('/pub/chat/message', {}, JSON.stringify({
            type: "TALK",
            roomId: roomId,
            message: msg.value,
            sender: username
        }));
        msg.value = '';
}

