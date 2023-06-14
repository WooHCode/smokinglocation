var sidebarContainer = document.querySelector(".sidebar-container");
var dragButton = document.querySelector(".drag-button");
var isDragging = false;
var startX, startY, initialX, initialY;
var lastTranslateX, lastTranslateY;
var touchX, touchY, translateX, translateY;
var containerWidth = sidebarContainer.offsetWidth;
var containerHeight = sidebarContainer.offsetHeight;
var windowWidth = window.innerWidth;
var windowHeight = window.innerHeight;
var minTranslateX = -containerWidth - 1200; // TODO 추후 수정 필요
var minTranslateY = -containerHeight;
var maxTranslateX = windowWidth;
var maxTranslateY = windowHeight;

dragButton.addEventListener("mousedown", dragStart);
dragButton.addEventListener("touchstart", dragStart, { passive: false });
document.addEventListener("mousemove", drag);
document.addEventListener("touchmove", drag, { passive: false });
document.addEventListener("mouseup", dragEnd);
document.addEventListener("touchend", dragEnd);

function dragStart(event) {
    event.preventDefault();
    if (event.type === "mousedown" && isMobileDevice()) {
        return;
    }
    isDragging = true;
    startX = event.clientX || event.touches[0].clientX;
    startY = event.clientY || event.touches[0].clientY;
    initialX = sidebarContainer.getBoundingClientRect().left;
    initialY = sidebarContainer.getBoundingClientRect().top;
    sidebarContainer.style.transition = "none";
    document.body.style.cursor = "grabbing";
    lastTranslateX =
        parseFloat(
            getComputedStyle(sidebarContainer).getPropertyValue("transform").split(",")[4]
        ) || 0;
    lastTranslateY =
        parseFloat(
            getComputedStyle(sidebarContainer).getPropertyValue("transform").split(",")[5]
        ) || 0;
}

function drag(event) {
    if (!isDragging) return;
    event.preventDefault();
    if (event.type === "mousemove" && isMobileDevice()) {
        return;
    }
    touchX = event.clientX || event.touches[0].clientX;
    touchY = event.clientY || event.touches[0].clientY;
    translateX = touchX - startX + lastTranslateX;
    translateY = touchY - startY + lastTranslateY;

    // 제한된 범위 설정
    translateX = Math.max(minTranslateX, Math.min(maxTranslateX - containerWidth, translateX));
    translateY = Math.max(minTranslateY, Math.min(maxTranslateY - containerHeight, translateY));

    sidebarContainer.style.transform = "translate(" + translateX + "px, " + translateY + "px)";
}

function dragEnd(event) {
    if (!isDragging) return;
    if (event.type === "mouseup" && isMobileDevice()) {
        return;
    }
    touchX = event.clientX || event.changedTouches[0].clientX;
    touchY = event.clientY || event.changedTouches[0].clientY;
    translateX = touchX - startX + lastTranslateX;
    translateY = touchY - startY + lastTranslateY;

    translateX = Math.max(minTranslateX, Math.min(maxTranslateX - containerWidth, translateX));
    translateY = Math.max(minTranslateY, Math.min(maxTranslateY - containerHeight, translateY));

    sidebarContainer.style.transform = "translate(" + translateX + "px, " + translateY + "px)";
    sidebarContainer.style.transition = "";
    document.body.style.cursor = "auto";

    lastTranslateX = translateX;
    lastTranslateY = translateY;

    isDragging = false;
}

function isMobileDevice() {
    return /Mobi|Android|iPhone|iPad|iPod/i.test(navigator.userAgent);
}

/*var wheelSensitivity = 0.1; // 휠 감도 조절 (조절 가능)

document.addEventListener("wheel", function(event) {
    event.preventDefault();

    var delta = event.deltaY || event.wheelDelta;
    var scrollAmount = delta * wheelSensitivity;

    var currentTransform = sidebarContainer.style.transform;
    var currentTranslateY = parseFloat(currentTransform.replace(/.*translateY\((.*)px\).*!/, "$1")) || 0;
    var newY = currentTranslateY + scrollAmount;

    sidebarContainer.style.transform = "translateY(" + newY + "px)";
});*/




