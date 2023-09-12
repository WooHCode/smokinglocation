

    /* long-polling 펑션 
     * 서버에서 전송 받은 데이터가 없으면 3초 주기로 계속 요청함
     * 서버에서 전송 받은 데이터가 있을 시 즉시 동작
     */
    function polling(){
		var refreshToken=sessionStorage.getItem("rf");
        $.ajax({
			//dataType: "text",
            type:'GET',
            url: "/polling-register",
            data: {
            	refreshToken: refreshToken
        	},
            success: function(data) {
                // do something with the data
                //console.log("return data :::: \n" + data);
                var notificationImg = document.getElementById("notification-img");
    			notificationImg.style.display = "show";
                setTimeout(polling(), 3600000);
            },
            error: function(request, status, error) {
                if (status=='timeout') {
                    //console.log( 'request timed out.' );
                    setTimeout(polling(), 3600000 );
                } else {
					//console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    setTimeout(polling(), 3600000 );
                }
            },
            timeout: 3600000 // 1시간 간격으로 확인 
            //timeout: 60000 // 60초 간격으로 확인 
        });
    }
    
