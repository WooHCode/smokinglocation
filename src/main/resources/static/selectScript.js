$('.selectGu').eq(0).on('change',function(){
    if($('.selectGu').eq(0).val()=='전체'){
        window.location.replace('/map');
    }else {
        window.location.replace('/map/select?gu=' + $('.selectGu').eq(0).val());
    }
})