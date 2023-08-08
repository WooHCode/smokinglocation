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