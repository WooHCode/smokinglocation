var endLatLng = [0,0];  //경도, 위도 순서
var myLatLng=[0,0];     //경도, 위도 순서
var getPathReady = false;   //길찾기 버튼 누르면 true (길찾기 준비상태)
var pathFound = false;
var map = null;
var polyline;

var nearbyIconUrl = '/image/location-icon-sign.png';
var nearbyIcon ={
    url: nearbyIconUrl,
};
var iconUrl = '/image/icons8-location-2--unscreen.gif';
var icon = {
    url: iconUrl,
};

var destinationIconUrl = '/image/destination-icon.png';
var destinationIcon = {
    url: destinationIconUrl,
};

var map = null;
var markers = [];
window.onload = function () {
    loadNaverMap(0,0);
    getCurrentPos(false);
    makeMyPosition(localStorage.getItem("lat"), localStorage.getItem("lng"));
};

function makeMyPosition(mylat, mylon) {
    if (localStorage.getItem("lat") !== "" && localStorage.getItem("lng") !== "") {
        var myLat = localStorage.getItem("lat");
        var myLng = localStorage.getItem("lng");
        var newMarker;
        newMarker = new naver.maps.Marker({
            position: new naver.maps.LatLng(myLat, myLng),
            map: map,
            icon: icon
        })
        markers.push(newMarker);
    }else {
        var newMarker;
        newMarker = new naver.maps.Marker({
            position: new naver.maps.LatLng(mylat, mylon),
            map: map,
            icon: icon
        })
        markers.push(newMarker);
    }
}
function hideLoading() {
    var loadingDiv = document.getElementById("loading");
    loadingDiv.style.display = "none";
}

function showLoading() {
    var loadingDiv = document.getElementById("loading");
    loadingDiv.style.display = "block";
}
function loadNaverMap(mylat, mylon) {
    if (mylat !== 0 && mylon !== 0) {
        map.setCenter(new naver.maps.LatLng(mylat, mylon));
        map.setZoom(15);
        makeMyPosition(mylat, mylon);
    } else {
        map = new naver.maps.Map('map', {
            center: new naver.maps.LatLng(37.5666805, 126.9784147),
            zoom: 11
        });
    }

    var tilesloadedListener = naver.maps.Event.addListener(map, 'tilesloaded', function () {
        hideLoading();
        naver.maps.Event.removeListener(tilesloadedListener); // 이벤트 핸들러 제거
    });
    for (var i = 0; i < facilities.length; i++) {
        var facility = facilities[i];
        var longitude = facility.lon;
        var latitude = facility.lat;
        var title = facility.location;
        var loc = facility.location;

        // 모든 속성값이 유효한 경우에만 마커 생성
        if (latitude && longitude && title && loc) {
            // 클로저를 사용하여 마커와 인포윈도우 연결
            (function (title, loc) {
                var lat = latitude;
                var lng = longitude;
                var marker = new naver.maps.Marker({
                    position: new naver.maps.LatLng(latitude, longitude),
                    title: loc,
                    map: map
                });
                var infoWindow = new naver.maps.InfoWindow({
                    content: loc
                });
                // 마커가 클릭되면 인포윈도우 열기
                naver.maps.Event.addListener(marker, 'click', function () {
                    infoWindow.open(map, marker);
                    endLatLng[0] = lng;
                    endLatLng[1] = lat;
                    console.log("endLatLng 위도 : " + endLatLng[1] + " 경도 : " + endLatLng[0]);
                    console.log("polyline : " + polyline);

                    if (getPathReady === true) {
                        showPath(myLatLng, endLatLng);

                        getPathReady = false;
                    }
                });
                markers.push(marker);
                // 마커 지도에 추가
                marker.setMap(map);
            })(title, loc);
        }
    }
}

function getCurrentPos(isClick) {
    showLoading();
    myLatLng = [0,0];
    if (isClick === true) {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                function (position) {
                    // 위치 정보를 가져오는 데 성공했을 때 처리할 로직
                    var latitude = position.coords.latitude;
                    var longitude = position.coords.longitude;
                    console.log("위도 =", latitude);
                    console.log("경도 =", longitude);
                    if (localStorage.getItem("lat") !== null && localStorage.getItem("lng") !== null) {
                        localStorage.removeItem("lat");
                        localStorage.removeItem("lng");
                    }
                    localStorage.setItem("lat", latitude);
                    localStorage.setItem("lng", longitude);
                    loadNaverMap(latitude, longitude);
                    hideLoading();
                    sendLocationData(latitude, longitude);
                    myLatLng[0] = longitude;
                    myLatLng[1] = latitude;
                    console.log("나의 위치 위도 : " + myLatLng[1] + " 경도 : " + myLatLng[0]);
                },
                function (error) {
                    // 위치 정보를 가져오는 데 실패했을 때 처리할 로직
                    console.error("위치 정보를 가져오는 데 실패했습니다.", error);
                    gotoHome();
                }
            );
        } else {
            // 브라우저에서 위치 기능을 지원하지 않을 때 처리할 로직
            console.error("이 브라우저는 위치 정보를 지원하지 않습니다.");
        }
    }
}

function readyGetPath(isClick) {
    var getPathButton = document.getElementById("getPath");
    getPathButton.classList.remove("hoverable");
    getPathButton.classList.remove("clicked");

    if (myLatLng[0] === 0){
        getCurrentPos(true);
    }
    if (isClick === true) {
        getPathReady = true;
        console.log("경로 찾기 준비 : 마커를 클릭하면 경로 표시");
        getPathButton.classList.add("clicked"); // 클릭한 상태에 해당하는 클래스 추가
    }
}

function showPath(myLatLng, endLatLng) {
    var getPathButton = document.getElementById("getPath");
    getPathButton.style.backgroundColor = "#86c0f8"; // 배경색 변경
    getPathButton.classList.remove("clicked"); // clicked 클래스 제거
    getPathButton.classList.add("hoverable"); // hoverable 클래스 추가

    // hover 이벤트를 다시 적용하기 위해 버튼에 마우스를 올렸다가 내려놓음
    getPathButton.addEventListener("mouseover", function () {
        this.style.backgroundColor = "#3393fc";
    });
    getPathButton.addEventListener("mouseout", function () {
        this.style.backgroundColor = "#86c0f8";
    });
    //경로 보여주기
    myLng = myLatLng[0];
    myLat = myLatLng[1];
    endLng = endLatLng[0];
    endLat = endLatLng[1];
    console.log("내 경도 : " + myLng)
    console.log("내 위도 : " + myLat)
    console.log("도착 경도 : " + endLng)
    console.log("도착 위도 : " + endLat)
    resetShowPath();
    changeDestinationMarker(endLat,endLng)
    $.ajax({
        url: '/directions',
        data: {
            myLng: myLatLng[0],
            myLat: myLatLng[1],
            endLng: endLatLng[0],
            endLat: endLatLng[1]
        }
    }).done(function (response) {
        console.log(response);
        pathFound = true;
        console.log("=========pathFound : " + pathFound + "==========");
        console.log("=========path 찾기 완료==========");
        // getPolyPath(response);
        console.log("======경로찾기 시작 : getPolyPath()======")
        var polyLinePath = [];
        console.log(response);
        for (var i = 0; i < response.length; i++) {
            var pathLat = response[i].lat;
            var pathLng = response[i].lng;
            polyLinePath.push(new naver.maps.LatLng(pathLat, pathLng));
        }
        console.log(polyLinePath);

        polyline = new naver.maps.Polyline({
            path: polyLinePath,      //선 위치 변수배열
            strokeColor: '#0083ea', //선 색 빨강 #빨강,초록,파랑
            strokeOpacity: 0.7, //선 투명도 0 ~ 1
            strokeWeight: 4,   //선 두께
            map: map           //오버레이할 지도
        });
        console.log("============polyline : " + polyline);
        console.log("============naver.maps.Polyline(경로) 오버레이 : Done");

        pathFound = false;
    });
}
function zoomInMap() {
    var zoom = map.getZoom() + 1;
    map.setZoom(zoom);
}

function zoomOutMap() {
    var zoom = map.getZoom() - 1;
    map.setZoom(zoom);
}

function sendLocationData(latitude, longitude) {
    var data = {
        latitude: latitude,
        longitude: longitude
    };
    $.ajax({
        type: "GET",
        url: "/map/nearby",
        data: data,
        contentType: "application/json",
        success: function (res) {
            for (var i = 0; i < markers.length; i++) {
                var marker = markers[i];
                for (var j = 0; j < res.length; j++) {
                    var latitude = res[j].latitude;
                    var longitude = res[j].longitude;
                    var markerPosition = marker.getPosition();
                    var markerLat = markerPosition.lat();
                    var markerLng = markerPosition.lng();

                    if (markerLat === parseFloat(latitude) &&
                        markerLng === parseFloat(longitude)) {
                        marker.setIcon(nearbyIcon);
                        break;
                    }
                }
            }
        },
        error: function (xhr, status, error) {
            console.error("위치 데이터 전송 중 오류 발생:", error);
        }
    });

}
function resetShowPath() {
    if (polyline) {
        polyline.setMap(null); // 기존에 출력한 polyline을 지도에서 제거
        polyline = null;
    }

    for (var i = 0; i < markers.length; i++) {
        var marker = markers[i];
        if (marker.getIcon()===destinationIcon) {
            marker.setIcon(nearbyIcon);
        }
    }
}


function changeDestinationMarker(endLat, endLng) {
    for (var i = 0; i < markers.length; i++) {
        var marker = markers[i];
        var markerPosition = marker.getPosition();
        var markerLat = markerPosition.lat();
        var markerLng = markerPosition.lng();

        if (markerLat === parseFloat(endLat) && markerLng === parseFloat(endLng)) {
            marker.setIcon(destinationIcon);
        }
    }
}

function gotoHome() {
    window.location.href = "/map";
}