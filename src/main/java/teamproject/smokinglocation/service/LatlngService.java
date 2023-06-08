package teamproject.smokinglocation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.controller.UrlData;
import teamproject.smokinglocation.dto.*;
import teamproject.smokinglocation.repository.LatlngRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LatlngService {
    private final LatlngRepository repository;

    public void checkUrlData(int urlData, List<?> res) {
        if (urlData == UrlData.YONGSAN.getNumber()) {
            log.info("===================saveYongsanData()==================");
            saveYongsanData((List<FacilityYongsan>) res);
        } else if (urlData == UrlData.YEONGDEUNGPO.getNumber()) {
            log.info("===================saveYeongdeungpoData()==================");
            saveYeongdeungpoData((List<FacilityYeongdeungpo>) res);
        } else if (urlData == UrlData.DONGJAK.getNumber()) {
            log.info("===================saveDongjakData()==================");
            saveDongjakData((List<FacilityDongjak>) res);
        } else if (urlData == UrlData.GURO.getNumber()) {
            log.info("===================saveGuroData()==================");
            saveGuroData((List<FacilityGuro>) res);
        } else if (urlData == UrlData.NOWON.getNumber()) {
            log.info("===================saveNowonData()==================");
            saveNowonData((List<FacilityNowon>) res);
        } else {
            log.info("===========ERROR OCCUR=========");
        }
    }
    public void saveYongsanData(List<FacilityYongsan> yongsanFacilities) {

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();
        for (Object item : yongsanFacilities) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        for (LinkedHashMap<String, String> data : dataList) {
            String latitude = data.get("위도");
            String longitude = data.get("경도");
            String location = data.get("서울특별시 용산구 설치 위치");
            log.info("================DATA INSERT=================");
            log.info("위도 = {}, 경도 = {}, 위치 = {}",latitude, longitude, location);

            repository.saveYongsan(latitude,longitude,location);
        }
    }

    public void saveYeongdeungpoData(List<FacilityYeongdeungpo> yeongdeungpoFacilities) {

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();
        for (Object item : yeongdeungpoFacilities) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        for (LinkedHashMap<String, String> data : dataList) {
            String latitude = data.get("위도");
            String longitude = data.get("경도");
            String location = data.get("시설 구분");
            log.info("================DATA INSERT=================");
            log.info("위도 = {}, 경도 = {}, 위치 = {}",latitude, longitude, location);

            repository.saveYeongdeungpo(latitude,longitude,"서울시 영등포구 "+location);
        }
    }

    public void saveDongjakData(List<FacilityDongjak> dongjakFacilities) {
        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();
        for (Object item : dongjakFacilities) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        for (LinkedHashMap<String, String> data : dataList) {
            String latitude = data.get("위도");
            String longitude = data.get("경도");
            String location = data.get("주소");
            log.info("================DATA INSERT=================");
            log.info("위도 = {}, 경도 = {}, 위치 = {}",latitude, longitude, location);

            repository.saveDongjak(latitude,longitude,location);
        }
    }
    public void saveGuroData(List<FacilityGuro> guroFacilities) {
        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();
        for (Object item : guroFacilities) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        for (LinkedHashMap<String, String> data : dataList) {
            String latitude = data.get("위도");
            String longitude = data.get("경도");
            String location = data.get("도로명주소");
            log.info("================DATA INSERT=================");
            log.info("위도 = {}, 경도 = {}, 위치 = {}",latitude, longitude, location);

            repository.saveGuro(latitude,longitude,location);
        }
    }
    public void saveNowonData(List<FacilityNowon> nowonFacilities) {
        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();
        for (Object item : nowonFacilities) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        for (LinkedHashMap<String, String> data : dataList) {
            String latitude = data.get("위도");
            String longitude = data.get("경도");
            String location = data.get("위치");
            log.info("================DATA INSERT=================");
            log.info("위도 = {}, 경도 = {}, 위치 = {}",latitude, longitude, location);

            repository.saveNowon(latitude,longitude,location);
        }
    }
}
