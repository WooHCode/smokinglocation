var endLatLng = [0,0];  //경도, 위도 순서
var myLatLng=[0,0];     //경도, 위도 순서
var pathLatLng = []; // 경도, 위도 순서
var getPathReady = false;   //길찾기 버튼 누르면 true (길찾기 준비상태)
var pathFound = false;
var map = null;
var polyline;
var isStopped = false; // 길찾기 현재위치 추적 반복(true일 시 추적)
const arrivalDistance = 5; // 도착으로 판단할 거리 (단위: 미터)

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
    // 사용자의 현재 위치 마커를 모두 제거
    for (var i = 0; i < markers.length; i++) {
        var marker = markers[i];
        if (marker.getIcon() === icon) {
            marker.setMap(null);
            markers.splice(i, 1);
            i--;
        }
    }

    // 새로운 사용자의 현재 위치 마커 생성
    var newMarker = new naver.maps.Marker({
        position: new naver.maps.LatLng(mylat, mylon),
        map: map,
        icon: icon
    });
    markers.push(newMarker);
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

                    // 마커 클릭 시, 해당 위치 정보를 저장 -> 마이페이지 최근 찾아본 흡연구역 리스트업
                    var data={
                        findLng:endLatLng[0],
                        findLat:endLatLng[1],
                        findLoc:loc,
                        refreshToken: sessionStorage.getItem('rf'),
                    }
                    console.log(data.findLng);
                    console.log(data.findLat);
                    console.log(data.findLoc);
                    $.ajax({
                        url:"/member/savespot",
                        type:"POST",
                        data:JSON.stringify(data),
                        contentType: "application/json",
                        success: function(){
                            console.log("클릭 위치 저장 완료");
                        }
                    })

                });
                markers.push(marker);
                // 마커 지도에 추가
                marker.setMap(map);
            })(title, loc);
        }
    }
    if(readySpotInfo){
        showSavedSpot();
    }
}


/**
 * 마이페이지에서 최근 본 위치 클릭하면 해당 위치 지도에서 보여줌
 */
function showSavedSpot() {
    console.log("showSavedSpot 진입========");
    console.log("최근 위치 찾기(readySpotInfo) : " + readySpotInfo);
    console.log("찾는 위치 Lng : " + spotInfo[0] + " / 찾는 위치 Lat : " + spotInfo[1]);
    map.setCenter(new naver.maps.LatLng(spotInfo[1], spotInfo[0]));
    map.setZoom(17);
    var marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(spotInfo[1], spotInfo[0]),
        title: spotInfo[2],
        map: map
    });
    var infoWindow = new naver.maps.InfoWindow({
        content: spotInfo[2]
    });
    naver.maps.Event.addListener(marker, "click", function () {
        infoWindow.open(map, marker);
    });

    spotInfo = null;
    readySpotInfo = null;
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

                    makeMyPosition(latitude,longitude);
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

function deleteCurrentMarker(myLat, myLng) {
    console.log("이전의 현재위치 마커 삭제")
    for (const marker of markers) {
        var markerPosition = marker.getPosition();
        var markerLat = markerPosition.lat();
        var markerLng = markerPosition.lng();

        if (markerLat === parseFloat(myLng) && markerLng === parseFloat(myLat)) {
            marker.setMap(null);
        }
    }
}
function getContinuousLoc() {
    deleteCurrentMarker(myLatLng[0],myLatLng[1])
    myLatLng = [0,0];
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                function (position) {
                    // 위치 정보를 가져오는 데 성공했을 때 처리할 로직
                    var latitude = position.coords.latitude;
                    var longitude = position.coords.longitude;
                    makeMyPosition(latitude,longitude)
                    myLatLng[0] = longitude;
                    myLatLng[1] = latitude;
                    onLocationUpdate(myLatLng)
                    console.log("나의 위치 위도 : " + myLatLng[1] + " 경도 : " + myLatLng[0]);
                },
                function (error) {
                    // 위치 정보를 가져오는 데 실패했을 때 처리할 로직
                    console.error("위치 정보를 가져오는 데 실패했습니다.", error);
                }
            );
        } else {
            // 브라우저에서 위치 기능을 지원하지 않을 때 처리할 로직
            console.error("이 브라우저는 위치 정보를 지원하지 않습니다.");
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
// 현재위치 추적 기능(파라미터가 true일때 5초마다 반복)
function replayCurrentMarkerMaker(isStopped) {
    if (isStopped){
        setInterval(function () {
            getContinuousLoc();
        }, 5000);
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
        pathFound = true;
        console.log("=========pathFound : " + pathFound + "==========");
        console.log("=========path 찾기 완료==========");
        // getPolyPath(response);
        console.log("======경로찾기 시작 : getPolyPath()======")
        var polyLinePath = [];
        for (var i = 0; i < response.length; i++) {
            var pathLat = response[i].lat;
            var pathLng = response[i].lng;
            pathLatLng.push([pathLng,pathLat])
            polyLinePath.push(new naver.maps.LatLng(pathLat, pathLng));
        }
        isStopped = true;
        replayCurrentMarkerMaker(isStopped);
        polyline = new naver.maps.Polyline({
            path: polyLinePath,      //선 위치 변수배열
            strokeColor: '#0083ea', //선 색 빨강 #빨강,초록,파랑
            strokeOpacity: 0.7, //선 투명도 0 ~ 1
            strokeWeight: 4,   //선 두께
            map: map           //오버레이할 지도
        });

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

//----------------------------------

// 현재 위치와 경로 사이의 거리를 계산하는 함수
function getDistance(myLat, myLon, targetLat, targetLon) {
    var R = 6371; // 지구 반경 (단위: km)
    var dLat = deg2rad(targetLat - myLat);
    var dLon = deg2rad(targetLon - myLon);
    var a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(myLat)) * Math.cos(deg2rad(targetLat)) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2);
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    var distance = R * c;
    return distance * 1000; // 단위를 m로 변환하여 반환함
}

function deg2rad(deg) {
    return deg * (Math.PI / 180);
}

// 현재 위치를 기준으로 경로를 그리고, 지나간 부분의 경로 라인을 제거하는 함수
function drawPathAndRemovePassedLines(myLatLng) {
    var myLat = myLatLng[1];
    var myLng = myLatLng[0];
    var partialPath = [];
    var passedLineIndex = -1; // 경로 상에서 지나간 부분의 마지막 좌표 인덱스

    // 현재 위치와 경로 사이의 거리를 계산하여 지나간 부분의 인덱스를 찾음
    for (var i = 0; i < pathLatLng.length; i++) {
        var pathLat = pathLatLng[i][1];
        var pathLng = pathLatLng[i][0];

        var distance = getDistance(myLat, myLng, pathLat, pathLng);
        if (distance < 10) { // 예시로 10m 이내로 지나갔다고 가정
            passedLineIndex = i;
        }
    }

    // 경로의 좌표 중 지나간 부분 이후의 좌표만 추출하여 새로운 경로로 설정
    if (passedLineIndex !== -1) {
        partialPath = pathLatLng.slice(passedLineIndex + 1);
    } else {
        partialPath = pathLatLng.slice(); // 지나간 부분이 없을 경우 전체 경로를 그대로 사용
    }

    // 기존 경로 삭제
    if (polyline) {
        polyline.setMap(null);
    }

    // 지나간 부분을 제외한 경로 다시 그리기
    polyline = new naver.maps.Polyline({
        path: partialPath.map(coord => new naver.maps.LatLng(coord[1], coord[0])),
        strokeColor: '#0083ea',
        strokeOpacity: 0.7,
        strokeWeight: 4,
        map: map
    });
    // 도착 여부 확인
    var lastPolylineIndex = partialPath.length - 1;
    var lastPolylineLat = partialPath[lastPolylineIndex][1];
    var lastPolylineLng = partialPath[lastPolylineIndex][0];
    checkArrival(myLatLng, lastPolylineLat,lastPolylineLng, arrivalDistance); // 5m를 도착 거리로 사용

}

// 사용자의 위치가 변경될 때마다 호출되는 함수
function onLocationUpdate(myLatLng) {
    console.log("경로 라인 변경")
    drawPathAndRemovePassedLines(myLatLng);
    makeMyPosition(myLatLng[1], myLatLng[0]);
}

// 도착 여부를 확인하는 함수
function checkArrival(myLatLng, lastPolylineLat ,lastPolylineLng, arrivalDistance) {
    var distanceToDestination = getDistance(myLatLng[1], myLatLng[0], lastPolylineLat, lastPolylineLng);
    console.log("차이값은? ",distanceToDestination)
    if (distanceToDestination <= arrivalDistance) {
        // 도착 알림 표시
        alert("도착하였습니다!");
        // 경로 및 현재 위치 추적 기능 종료
        isStopped = false;
        if (polyline) {
            polyline.setMap(null);
            polyline = null;
        }
    }
}


