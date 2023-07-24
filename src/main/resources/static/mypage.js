var refreshToken = sessionStorage.getItem("rf");

function toMypage(){
    console.log("toMypage in")
    $.ajax({
        url:"/mypage",
        type:"GET",
        data: {
            refreshToken: refreshToken
        },
        success: function(res){
            window.location.replace("/mypage/" + res);
        }
    })
}