function toMypage(){
    var refreshToken=sessionStorage.getItem("rf")
    $.ajax({
        url:"/member/auth",
        type:"GET",
        data: {
            refreshToken: refreshToken,
        },
        success: function(res){
            location.href="/member/" + res;
        }
    });
}

function toAdminPage(){
    var refreshToken=sessionStorage.getItem("rf")
    $.ajax({
        url:"/member/auth",
        type:"GET",
        data: {
            refreshToken: refreshToken,
        },
        success: function(res){
            toAdminPageCheckRole(res);
        }
    });
}

function toAdminPageCheckRole(res){
    $.ajax({
        url:"/member/auth-admin",
        type:"GET",
        data: {
            id: res,
        },
        success: function(res){
            if (res==="ADMIN")
            window.location.replace("/member/admin");
        }
    });
}

function toUpdate(){
    var refreshToken=sessionStorage.getItem("rf")
    $.ajax({
        url:"/member/auth",
        type:"GET",
        data: {
            refreshToken: refreshToken,
        },
        success: function(res){
            window.location.replace("/member/mypage/info-update");
        }
    });
}

$(".password-change-submit").on("click", function() {
    var pwChange = $("#pw-change").val();
    var pwChangeRe = $("#pw-change-re").val();
    if (pwChange !== pwChangeRe) {
        alert("비밀번호 재입력이 비밀번호와 일치하지 않습니다.");
        $("#pw-change").focus();
        return false;
    } else{
        alert("비밀번호 변경 완료!");
        return true;
    }
});