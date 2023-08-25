const btn = document.querySelector('.selectGu');
const list = document.querySelector('.selectGu-option');

btn.addEventListener('click', () => {
    btn.classList.toggle('on');
});

list.addEventListener('click', (event) => {
    if (event.target.nodeName === "BUTTON") {
		if($('.selectGu').eq(0).val()=='전체'){
	        window.location.replace('/map');
	    }else {
	        window.location.replace('/map/select?gu='+event.target.innerText);
	    }
        btn.classList.remove('on');
    }
});
