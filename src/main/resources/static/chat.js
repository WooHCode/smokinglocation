var roomId = localStorage.getItem("rmId");
var username = localStorage.getItem("temp");
let sockJs = null
let stomp = null

function onConnected() {
    console.log("연결됐니?? "+ stomp.connected)
    stomp.subscribe("/sub/chat/room/" + roomId, function (chat) {
        console.log("여기는 도착했니?")
        var content = JSON.parse(chat.body);
        console.log("receive message = " + content.message)
        var sender = content.sender;
        var str = '';
        if (sender === username) {
            str = "<div class='col-6'>";
            str += "<div class='alert alert-secondary'>";
            str += "<b>" + sender + " : " + content.message + "</b>";
            str += "</div></div>";
            $("#message-user").append(str);
        } else {
            str = "<div class='col-6'>";
            str += "<div class='alert alert-warning'>";
            str += "<b>" + sender + " : " + content.message + "</b>";
            str += "</div></div>";
            $("#message-admin").append(str);
        }
    });
    /*stomp.send('/pub/chat/message', {}, JSON.stringify({type: "ENTER", roomId: roomId, sender: username}))*/
}
function afterChatPopupLoaded () {
    sockJs = new SockJS("/stomp/chat");
//1. SockJS를 내부에 들고있는 stomp를 내어줌
    stomp = Stomp.over(sockJs);
    console.log(roomId + ", " + username);
    stomp.connect({}, onConnected, function (error) {
        console.log("stomp error: "+error)
    });

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

