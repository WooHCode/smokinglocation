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
    if (confirm("채팅방을 나가시겠습니까?")) {
        var popup = document.getElementById("chatPopup");
        popup.style.visibility = "hidden";
        popup.style.opacity = "0";

        $.ajax({
            url: "/chat/room/clear",
            type: "POST",
            data:{
                roomId : localStorage.getItem("rmId")
            },
            success: function () {
                console.log("채팅방삭제")
                location.reload();
            }, error: function () {
                location.reload();
            },
        })
    }
}

function getChatPopup() {
    $.ajax({
        url: "/chat/room",
        type: "POST",
        data: {
            name: localStorage.getItem("temp"),
            rf: sessionStorage.getItem("rf")
        },
        success: function (res) {
            localStorage.setItem("rmId", res);
            console.log(res)
        },
    })
    $.ajax({
        url: "/chat/room/enter/" + localStorage.getItem("rmId"),
        type: "GET",
        success: function (res, status, xhr) {
            closeBoardPopup()
            var boardContent = document.getElementById("board");
            boardContent.innerHTML = res;
            boardContent.style.zIndex = "9999"
            openChatPopup()
            afterChatPopupLoaded()
            var sendingButton = document.getElementById("button-send")
            sendingButton.style.display = "none"
            var connectionButton = document.getElementById("startChat")
            connectionButton.style.display = "block"
        },
        error: function (xhr, status, error) {
            if (xhr.status === 403) {
                window.alert("로그인 후 이용가능합니다.")
            }
        }
    })
}

function shopingPopup() {
    alert("준비중입니다.")
}

function board() {
    console.log("board() 진입");
    var accessToken = localStorage.getItem("at");
    $.ajax({
        url: "/customerService",
        type: "GET",
        headers: {
            Authorization: "Bearer " + accessToken,
        },
        success: function (res, status, xhr) {
            refreshEveryTokens(xhr);
            var boardContent = document.getElementById("board");

            boardContent.innerHTML = res;
            openBoardPopup();
            boardContent.style.zIndex = "9999";
        },
        error: function (xhr, status, error) {
            if (xhr.status === 403) {
                window.alert("로그인 후 이용가능합니다.")
            }
        }
    });
}

function getBoardPopup() {
    var rf = sessionStorage.getItem('rf');
    var data = {
        rf: rf,
    }
    console.log(data);
    $.ajax({
        url: "/main-board",
        type: "GET",
        data: data,
        success: function (res, status, xhr) {
            var boardContent = document.getElementById("board");
            boardContent.innerHTML = res;
            openBoardPopup();
            boardContent.style.zIndex = "9999";
        },
        error: function (xhr, status, error) {
            if (xhr.status === 403) {
                window.alert("로그인 후 이용가능합니다.")
            }
        }
    });
}

function getBoardSubmitForm() {
    var memberId = $('#clientEmail').val();
    var content = $('#boardContent').val()
    $.ajax({
        url: "/board",
        type: "POST",
        data: {
            memberId: memberId,
            content: content
        },
        success: function (res, status, xhr) {
            var boardContent = document.getElementById("board");
            boardContent.innerHTML = res;
            openBoardPopup();
            boardContent.style.zIndex = "9999";
        },
        error: function (xhr, status, error) {
            if (xhr.status === 403) {
                window.alert("로그인 후 이용가능합니다.")
            }
        }
    });

    $.ajax({
        url: "/main-ask-complete",
        type: "GET",
        data: {
            refreshToken: sessionStorage.getItem("rf"),
            content: content
        },
        success: function (res, status, xhr) {
            var boardContent = document.getElementById("board");
            boardContent.innerHTML = res;
            openBoardPopup();
            boardContent.style.zIndex = "9999";
        },
        error: function (xhr, status, error) {
            if (xhr.status === 403) {
                window.alert("로그인 후 이용가능합니다.")
            }
        }
    });
}

function sendReply() {

}