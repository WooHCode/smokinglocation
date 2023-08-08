var sock = new SockJS("/ws-stomp");
var ws = Stomp.over(sock);
var reconnect = 0;
var messages = [];

var roomId = ""
function refreshEveryTokens(xhr) {
    var newAccessToken = xhr.getResponseHeader("X-Access-Token");
    var newRefreshToken = xhr.getResponseHeader("X-Refresh-Token");

    if (newAccessToken && newRefreshToken) {
        localStorage.setItem("at", newAccessToken);
        sessionStorage.setItem("rf", newRefreshToken);
    }
}
function openBoardPopup() {
    var popup = document.getElementById("boardPopup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
}

function closeBoardPopup() {
    var popup = document.getElementById("boardPopup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}
function openChatPopup() {
    var popup = document.getElementById("chatPopup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
}

function closeChatPopup() {
    var popup = document.getElementById("chatPopup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

function getChatPopup() {
    $.ajax({
        url: "/chat/room",
        type: "POST",
        data: {
            name: "myname"
        },
        success: function (res) {
            roomId = res;
            console.log(roomId)
        },
    })
    $.ajax({
        url:"/chat/room/enter/"+roomId,
        type:"GET",
        success: function (res, status, xhr) {
            var boardContent = document.getElementById("board");
            closeBoardPopup()
            boardContent.innerHTML = res;
            boardContent.style.zIndex="9999"
            openChatPopup()
            connect()
        },
        error: function (xhr, status, error){
            if (xhr.status === 403) {
                window.alert("로그인 후 이용가능합니다.")
            }
        }
    })
}

function connect() {
    ws.connect({}, function (frame) {
        ws.subscribe("/sub/chat/room/" + roomId, function (message) {
            var recv = JSON.parse(message.body);
            receiveMessage(recv)
            var chatElement = document.createElement("div");
            chatElement.classList.add(recv.sender === localStorage.getItem("temp") ? "message user" : "message admin");
            var chatText = document.createElement("p");
            chatText.innerText = recv.message;
            chatElement.appendChild(chatText);
            chatWindow.appendChild(chatElement);

            // 채팅 창 스크롤을 맨 아래로 이동
            chatWindow.scrollTop = chatWindow.scrollHeight;

            if (recv.sender === localStorage.getItem("temp")) {
                displaySentMessage(recv.message);
            }
        });
        ws.send("/pub/chat/message", {}, JSON.stringify({type:'ENTER', roomId:roomId, sender:messages.sender}));
    },function(error) {
        console.log("error occur!!" + error)
        if(reconnect++ <= 5) {
            setTimeout(function() {
                console.log("connection reconnect");
                sock = new SockJS("/ws-stomp");
                ws = Stomp.over(sock);
                connect();
            },10*1000);
        }
    });
}
function sendMessage() {
    connect()
    var message = document.getElementById("message").value;
    var data = JSON.stringify({ type: 'TALK', roomId: roomId, sender: localStorage.getItem("temp"), message: message });
    ws.send("/pub/chat/message", {}, data);
    var initMessage = document.getElementById("message-user");
    if (initMessage.innerText === '') {
        initMessage.innerText = message;
    } else {
        displaySentMessage(message);
    }
    document.getElementById("message").value = '';
}

function receiveMessage(recv) {
    var adminMessage = document.getElementById("message-admin")
    adminMessage.value = recv.message
    messages.unshift({"type":recv.type,"sender":recv.type=='ENTER'?'[알림]':recv.sender,"message":recv.message})
}

function displaySentMessage(message) {
    console.log("displaySentMessage = " + message)
    var chatWindow = document.getElementById("chatWindow");
    var chatElement = document.createElement("div");
    chatElement.classList.add("message", "user");
    var chatText = document.createElement("p");
    chatText.innerText = message;
    chatElement.appendChild(chatText);
    chatWindow.appendChild(chatElement);

    // 채팅 창 스크롤을 맨 아래로 이동
    chatWindow.scrollTop = chatWindow.scrollHeight;
}

function board() {
    var accessToken = localStorage.getItem("at");
    $.ajax({
        url: "/board",
        type: "GET",
        headers: {
            Authorization : "Bearer " + accessToken,
        },
        success: function (res, status, xhr) {
            refreshEveryTokens(xhr);
            var boardContent = document.getElementById("board");

            boardContent.innerHTML = res;
            openBoardPopup();
            boardContent.style.zIndex = "9999";
        },
        error: function (xhr, status, error){
            if (xhr.status === 403) {
                window.alert("로그인 후 이용가능합니다.")
            }
        }
    });
}