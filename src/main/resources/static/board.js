var sockJs = new SockJS("/ws-stomp")
var stomp = Stomp.over(sockJs)

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
    connect()
}

function closeChatPopup() {
    var popup = document.getElementById("chatPopup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}
function connect() {
    stomp.connect({}, function () {
        console.log("stomp connection")
        stomp.subscribe("/sub/chat/room" + roomId, function (chat){
            var content = JSON.parse(chat.body)

            var sender = content.sender;
            var str = '';

            if (sender === localStorage.getItem("temp")){
                str += "<div class = 'col-6>";
                str += "<b>" + sender + " : " + content.message + "</b>"
                str += "</div>"

            }
        })
    })
}
function getChatPopup() {
    $.ajax({
        url: "/chat/room",
        type: "POST",
        data: {
            name: localStorage.getItem("temp")
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
            closeBoardPopup()
            var boardContent = document.getElementById("board");
            boardContent.innerHTML = res;
            boardContent.style.zIndex="9999"
            openChatPopup()
        },
        error: function (xhr, status, error){
            if (xhr.status === 403) {
                window.alert("로그인 후 이용가능합니다.")
            }
        }
    })
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