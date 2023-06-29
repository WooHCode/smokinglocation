function openPopup() {
    var popup = document.getElementById("popup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
}

function closePopup() {
    var popup = document.getElementById("popup");
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
        console.log(res);
    })
}
$("#register-form").submit(function (event) {
        event.preventDefault();

        var name = document.getElementById("name").value;
        var email = document.getElementById("emailInput").value;
        var password = document.getElementById("password").value;

        console.log(name)
        console.log(email)
        console.log(password)

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