
	function openNotification() {
		alert("알람생성");
		
	}

   /* long-polling 펑션 
     * 서버에서 전송 받은 데이터가 없으면 3초 주기로 계속 요청함
     * 서버에서 전송 받은 데이터가 있을 시 즉시 동작
     */
    function poll(dossierId){
        $.ajax({
            url: "register/"+dossierId,
            success: function(data) {
                // do something with the data
                console.log(data);
                $('#results').append("<li style=\"color:purple;\"><pre>"+JSON.stringify(JSON.parse(data), null, 4)+"</pre></li>");
                setTimeout( poll(dossierId), 10 );
            },
            error: function(status) {
                if (status=='timeout') {
                    console.log( 'request timed out.' );
                    setTimeout( poll(dossierId), 10 );
                }                else {
                    console.log(status);
                    setTimeout( poll(dossierId), 60000 );
                }
            },
            dataType: "text",
            type:'GET',
            timeout: 3000 // 3초 간격으로 확인 
            //timeout: 60000 // 60초 간격으로 확인 
        });
    }
    
    /*
    테스트용 
    */
    
    function simulate(dossierId){
        $.ajax({
            url: "simulate/"+dossierId,
            success: function(data, status, jqXHR) {
                // do something with the data
                console.log("Event simulated successfully.");
                alert("Event simulated successfully.");
            },
            type:'POST'
        });
    }