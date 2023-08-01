var checkEmailMessage;
var login = document.getElementById("login");
var logout = document.getElementById("logout");
var register = document.getElementById("register");
var mypage = document.getElementById("mypage");

function openPopup() {
    var popup = document.getElementById("popup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
}

function openRegPopup() {
    var popup = document.getElementById("loginPopup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
}

function closeLoginPopup() {
    var popup = document.getElementById("loginPopup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "1";
}
function visibleLogout() {
    var isLoggedIn = localStorage.getItem("isLoggedIn");
    if (isLoggedIn === "true") {
        login.style.display = "none";
        logout.style.display = "block";
		register.style.display = "none";
        mypage.style.display = "block";
    }else {
        login.style.display = "block";
        logout.style.display = "none";
		register.style.display = "block";
        mypage.style.display = "none";
    }
}
window.addEventListener("DOMContentLoaded", function() {
    visibleLogout();
});

function closePopup() {
    var popup = document.getElementById("popup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

function closeRegPopup() {
    var popup = document.getElementById("loginPopup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

function checkEmailIdDuplicate() {
    var emailInput = document.getElementById("emailInput");
    var email = emailInput.value;

    $.ajax({
        url:'/check-email',
        data: {
            email : email
        }
    }).done(function (res){
        checkEmailMessage = document.getElementById("check-email");
        checkEmailMessage.innerText = res;
        if (checkEmailMessage.innerText === "이메일 형식으로 입력해주세요.") {
            checkEmailMessage.style.color="blue";
        } else if (checkEmailMessage.innerText === "이미 가입된 아이디 입니다.") {
            checkEmailMessage.style.color="red";
        } else {
            checkEmailMessage.style.color = "black";
        }
    })
}
$("#register-form").submit(function (event) {
        event.preventDefault();

        var name = document.getElementById("name").value;
        var email = document.getElementById("emailInput").value;
        var password = document.getElementById("password").value;

    if (checkEmailMessage.innerText === "이미 가입된 아이디 입니다.") {
        alert("이미 가입된 아이디 입니다.")
        return;
    }

        var formData = {
            name: name,
            email: email,
            password: password
        };

        $.ajax({
            url: "/register-member",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function (res) {
                if (res === "success") {
                    window.alert("회원가입에 성공하였습니다.")
                    gotoHome();
                }
            },
            error: function (xhr, status, error){
                console.error("등록실패! : ",error)
            }
        });
});

$("#login-form").submit(function (event) {
    event.preventDefault();

    var email = document.getElementById("loginEmail").value;
    var password = document.getElementById("loginPassword").value;


    var formData = {
        email: email,
        password: password
    };

    $.ajax({
        url: "/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function (res) {
            if (res.status === "OK") {
                localStorage.setItem("at",res.accessToken);
                sessionStorage.setItem("rf", res.refreshToken);
                window.alert("로그인에 성공하였습니다.")
                localStorage.setItem("isLoggedIn","true");
                visibleLogout();
                closeLoginPopup();
            }
        },
        error: function (xhr, status, error){
            if (xhr.status === 403) {
                window.alert("아이디와 패스워드를 확인해주세요.")
            } else {
                console.error("error : ", error);
            }
        }
    });
});

function funLogout() {
    var accessToken = localStorage.getItem("at");
    $.ajax({
        url: "/loggout",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            accessToken: accessToken
        }),
        success: function(res) {
            alert(res+"님 로그아웃 되었습니다.")
        },
    });
    localStorage.removeItem("isLoggedIn");
    localStorage.removeItem("at");
    sessionStorage.removeItem("rf");
    visibleLogout();
}
// 카카오 소셜 로그인 호출 함수
function kakaoLogin(provider) {
    $.ajax({
        url: "/getSocialAuthUrl", // 서버에서 카카오 인증 URL을 가져오는 엔드포인트
        type: "POST",
        data: { "provider": provider }, // provider 값을 JSON 데이터로 만들어 전달
        success: function(res) {
			// 카카오 소셜 로그인 URL을 생성하여 리다이렉트
			window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=" + res.clientId + "&redirect_uri=" + res.redirectUrl + "&response_type=code";
        },
        error: function(xhr, status, error) {
			// 오류가 발생한 경우 사용자에게 알림
            alert("카카오 로그인 오류가 발생했습니다.");
        }
    });
}

// 카카오 소셜 로그인 호출 함수
function naverLogin(provider) {
	    $.ajax({
        url: "/getSocialAuthUrl", // 서버에서 카카오 인증 URL을 가져오는 엔드포인트
        type: "POST",
        data: { "provider": provider }, // provider 값을 JSON 데이터로 만들어 전달
        success: function(res) {
			// 카카오 소셜 로그인 URL을 생성하여 리다이렉트
			window.location.href = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+res.clientId+"&state=STATE_STRING&redirect_uri="+res.redirectUrl;
        },
        error: function(xhr, status, error) {
			// 오류가 발생한 경우 사용자에게 알림
            alert("네이버 로그인 오류가 발생했습니다.");
        }
    });
}

// 구글 소셜 로그인 호출 함수
function googleLogin(provider) {
	    $.ajax({
        url: "/getSocialAuthUrl", // 서버에서 카카오 인증 URL을 가져오는 엔드포인트
        type: "POST",
        data: { "provider": provider }, // provider 값을 JSON 데이터로 만들어 전달
        success: function(res) {
			// 구글 소셜 로그인 URL을 생성하여 리다이렉트
			window.location.href = "https://accounts.google.com/o/oauth2/v2/auth?client_id="+res.clientId+"&redirect_uri="+res.redirectUrl+"&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email";;
        },
        error: function(xhr, status, error) {
			// 오류가 발생한 경우 사용자에게 알림
            alert("구글 로그인 오류가 발생했습니다.");
        }
    });
}