package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import teamproject.smokinglocation.dto.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DtoTestController {

    private final MapController mapController;

    @GetMapping("/test/map")
    public void jsonToDtoTest() {
        log.info("==================광진구====================");
        for (Object facility : mapController.<FacilityGwangjin>fetchData(UrlData.GWANGJIN.getNumber(), UuidData.GWANGJIN.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================용산구====================");
        for (Object facility : mapController.<FacilityYongsan>fetchData(UrlData.YONGSAN.getNumber(), UuidData.YONGSAN.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================영등포구====================");
        for (Object facility : mapController.<FacilityYeongdeungpo>fetchData(UrlData.YEONGDEUNGPO.getNumber(), UuidData.YEONGDEUNGPO.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================중구====================");
        for (Object facility : mapController.<FacilityJunggu>fetchData(UrlData.JUNGGU.getNumber(), UuidData.JUNGGU.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================강서구====================");
        for (Object facility : mapController.<FacilityGangseo>fetchData(UrlData.GANGSEO.getNumber(), UuidData.GANGSEO.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================송파구====================");
        for (Object facility : mapController.<FacilitySongpa>fetchData(UrlData.SONGPA.getNumber(), UuidData.SONGPA.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================서대문구====================");
        for (Object facility : mapController.<FacilitySeodaemun>fetchData(UrlData.SEODAEMUN.getNumber(), UuidData.SEODAEMUN.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================중랑구====================");
        for (Object facility : mapController.<FacilityJungnang>fetchData(UrlData.JUNGNANG.getNumber(), UuidData.JUNGNANG.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================양천구====================");
        for (Object facility : mapController.<FacilityYangcheon>fetchData(UrlData.YANGCHEON.getNumber(), UuidData.YANGCHEON.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================동작구====================");
        for (Object facility : mapController.<FacilityDongjak>fetchData(UrlData.DONGJAK.getNumber(), UuidData.DONGJAK.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================관악구====================");
        for (Object facility : mapController.<FacilityGwanak>fetchData(UrlData.GWANAK.getNumber(), UuidData.GWANAK.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================동대문구====================");
        for (Object facility : mapController.<FacilityDongdaemun>fetchData(UrlData.DONGDAEMUN.getNumber(), UuidData.DONGDAEMUN.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================구로구====================");
        for (Object facility : mapController.<FacilityGuro>fetchData(UrlData.GURO.getNumber(), UuidData.GURO.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================강북구====================");
        for (Object facility : mapController.<FacilityGangbuk>fetchData(UrlData.GANGBUK.getNumber(), UuidData.GANGBUK.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================성동구====================");
        for (Object facility : mapController.<FacilitySeongdong>fetchData(UrlData.SEONGDONG.getNumber(), UuidData.SEONGDONG.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================서초구====================");
        for (Object facility : mapController.<FacilitySeocho>fetchData(UrlData.SEOCHO.getNumber(), UuidData.SEOCHO.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================성북구====================");
        for (Object facility : mapController.<FacilitySeongbuk>fetchData(UrlData.SEONGBUK.getNumber(), UuidData.SEONGBUK.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================노원구====================");
        for (Object facility : mapController.<FacilityNowon>fetchData(UrlData.NOWON.getNumber(), UuidData.NOWON.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================강남구====================");
        for (Object facility : mapController.<FacilityGangnam>fetchData(UrlData.GANGNAM.getNumber(), UuidData.GANGNAM.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }
        log.info("==================종로구====================");
        for (Object facility : mapController.<FacilityJongno>fetchData(UrlData.JONGNO.getNumber(), UuidData.JONGNO.getUuid()).getData()) {
            log.info("{}", facility.toString());
        }

    }

}
