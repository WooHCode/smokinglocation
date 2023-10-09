var checkEmailMessage;
var login = document.getElementById("login");
var logout = document.getElementById("logout");
var register = document.getElementById("register");
var mypage = document.getElementById("mypage");
var adminPage = document.getElementById("adminPage");

/* 회원가입 활성화 함수*/
function openPopup() {
    var popup = document.getElementById("popup");
	var popup2 = document.getElementById("loginPopup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
    popup2.style.visibility = "hidden";
    popup2.style.opacity = "0";
}

/* 로그인 활성화 함수*/
function openRegPopup() {
    var popup = document.getElementById("loginPopup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
}

/* 비밀번호 찾기 결과 활성화 함수*/
function findPwResultPopup() {
    var popup = document.getElementById("findPwResultPopup");
    var popup2 = document.getElementById("findPwPopup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
    popup2.style.visibility = "hidden";
    popup2.style.opacity = "0";
}

function closeLoginPopup() {
    var popup = document.getElementById("loginPopup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "1";
}

/* 비밀번호 찾기 활성화 함수*/
function findPw() {
    var popup = document.getElementById("findPwPopup");
    var popup2 = document.getElementById("loginPopup");
	popup2.style.visibility = "hidden";
    popup2.style.opacity = "0";
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
}

function visibleLogout() {
    var isLoggedIn = localStorage.getItem("isLoggedIn");
    var roles = localStorage.getItem("roles");
    if (isLoggedIn === "true") {
        login.style.display = "none";
        logout.style.display = "block";
		register.style.display = "none";
		if(roles == "ADMIN"){
	        adminPage.style.display = "block";
        	mypage.style.display = "none";
		}else{
			adminPage.style.display = "none";
	        mypage.style.display = "block";
		}
    }else {
        login.style.display = "block";
        logout.style.display = "none";
		register.style.display = "block";
        mypage.style.display = "none";
        adminPage.style.display = "none";
    }
}
window.addEventListener("DOMContentLoaded", function() {
    visibleLogout();
});

/* 회원가입 닫기*/
function closePopup() {
    var popup = document.getElementById("popup");
    $("#name").val("");
    $("#emailInput").val("");
    $("#password").val("");
    $("#password2").val("");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

/* 로그인 닫기*/
function closeRegPopup() {
    var popup = document.getElementById("loginPopup");
    $("#loginEmail").val("");
    $("#loginPassword").val("");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

/* 비밀번호 찾기 닫기 */
function closeFindPwPopup() {
    var popup = document.getElementById("findPwPopup");
    $("#findPwEmail").val("");
    $("#findPwName").val("");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

/* 비밀번호 찾기 결과 닫기*/
function closeFindPwResultPopup() {
    var popup = document.getElementById("findPwResultPopup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

/* 이메일 유효성 체크*/
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

/* 비밀번호 찾기 함수*/
function fnFindPw() {
	var email = document.getElementById("findPwEmail").value;
    var name = document.getElementById("findPwName").value;

    var formData = {
        email: email,
        name: name
    };

    $.ajax({
        url: "/member/findPw",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function (res) {
			if(res === "Success") {
				// 비밀번호 찾기 성공
				findPwResultPopup();	
			} else if(res === "fail") {
				// 비밀번호 찾기 실패
				alert("정보가 일치하지 안습니다.");
				closeFindPwPopup();
			}   
        }
    });
}

/* 회원가입*/
function fnRegister() {
	var name = document.getElementById("name").value;
	var email = document.getElementById("emailInput").value;
	var password = document.getElementById("password").value;
    var password2 = document.getElementById("password2").value;
    var passwordMismatchMessage = document.getElementById("password-mismatch-message");

    // 모든 필드가 비어 있는지 확인
    if (!name || !email || !password || !password2) {
        alert("모든 필수 필드를 입력하세요.");
        return; // 필수 필드가 비어 있으면 함수 종료
    }
	
	if (password !== password2) {
        passwordMismatchMessage.innerText = "비밀번호가 일치하지 않습니다.";
        return; // 일치하지 않으면 함수 종료
    } else {
        passwordMismatchMessage.innerText = ""; // 일치하면 메시지 지우기
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
			alert("gggg");
            if (res === "success") {
                window.alert("회원가입에 성공하였습니다.")
                gotoHome();
            }
        },
        error: function (xhr, status, error){
            console.error("등록실패! : ",error)
        }
    });
}

/* 로그인 */
function fnLogin() {
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
                localStorage.setItem("roles", res.roles);
                sessionStorage.setItem("rf", res.refreshToken);
                window.alert("로그인에 성공하였습니다.")
                localStorage.setItem("isLoggedIn","true");
                visibleLogout();
                closeLoginPopup();
                polling();
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
}

/* 로그아웃 */
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
            localStorage.setItem("temp", res);
            location.href = '/map';
        },
    });
    localStorage.removeItem("isLoggedIn");
    localStorage.removeItem("OAuthToken");
    localStorage.removeItem("provider");
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
			window.location.href = "https://accounts.google.com/o/oauth2/v2/auth?client_id="+res.clientId+"&redirect_uri="+res.redirectUrl+"&response_type=code&scope=https://www.googleapis.com/auth/drive.metadata.readonly";
        },
        error: function(xhr, status, error) {
			// 오류가 발생한 경우 사용자에게 알림
            alert("구글 로그인 오류가 발생했습니다.");
        }
    });
}

function fnLoginTgPw() {
    var passwordInput = document.getElementById("loginPassword");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }
}

function fnResiterTgPw() {
    var passwordInput = document.getElementById("password");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }
}

function fnResiterTgPw2() {
    var passwordInput = document.getElementById("password2");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }
}
