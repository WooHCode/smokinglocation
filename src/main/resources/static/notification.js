
	function openNotification() {
		alert("알람생성");
		
	}

    /* long-polling 펑션 
     * 서버에서 전송 받은 데이터가 없으면 3초 주기로 계속 요청함
     * 서버에서 전송 받은 데이터가 있을 시 즉시 동작
     */
    function poll(){
		var refreshToken=sessionStorage.getItem("rf");
        $.ajax({
			//dataType: "text",
            type:'GET',
            url: "/register",
            data: {
            	refreshToken: refreshToken,
        	},
            success: function(data) {
                // do something with the data
                console.log("return data :::: \n" + data);
                setTimeout(poll(), 10);
            },
            error: function(request, status, error) {
                if (status=='timeout') {
                    //console.log( 'request timed out.' );
                    setTimeout(poll(), 10 );
                } else {
					//console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    setTimeout(poll(), 60000 );
                }
            },
            timeout: 3600000 // 1시간 간격으로 확인 
            //timeout: 60000 // 60초 간격으로 확인 
        });
    }
    
    /*
    테스트용 
    */
    
    function simulate(){
		var refreshToken=sessionStorage.getItem("rf");
        $.ajax({
            url: "/simulate",
            data: {
            	refreshToken: refreshToken,
        	},
            success: function(data, status, jqXHR) {
                // do something with the data
                console.log("Event simulated successfully.");
            },
            type:'POST'
        });
    }