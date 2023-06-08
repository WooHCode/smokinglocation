package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import teamproject.smokinglocation.dto.*;
import teamproject.smokinglocation.entity.Dongjak;
import teamproject.smokinglocation.service.DataResponseService;
import teamproject.smokinglocation.service.LatlngService;
import teamproject.smokinglocation.service.ScheduleDataService;
import teamproject.smokinglocation.service.TotalDataService;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DataScheduler {
    private final DataResponseService responseService;
    private final ScheduleDataService scheduleDataService;
    private final LatlngService latlngService;
    private final DataFetchController controller;
    private final TotalDataService totalDataService;
    /**
     * second
     * minute
     * hour
     * day of month
     * month
     * day of week
     * 매월 15일 새벽 4시에 실행
     */
    @Scheduled(cron = "* * 4 15 * *")
    public void monthlyDataFetch() {
        //캐시 데이터 삭제
        log.info("==============cacheDataDelete================");
        responseService.deleteTotalDataCache();
        log.info("==============OldDataDelete================");
        scheduleDataService.deleteOldData();
        log.info("==============newDatafetch================");
        for (int i = 0; i < UrlData.values().length; i++) {
            UrlData urlData = UrlData.values()[i];
            System.out.println("urlData = " + urlData.getNumber());
            UuidData uuidData = UuidData.values()[i];
            System.out.println("uuidData = " + uuidData.getUuid());
            FacilityData<?> data;
            switch (urlData){
                case DONGJAK:
                    FacilityData<FacilityDongjak> dongjak = controller.fetchData(urlData.getNumber(), uuidData.getUuid());
                    data = dongjak;
                    log.info("============ {} END=============",urlData);
                    latlngService.checkUrlData(urlData.getNumber(), data.getData());
                    break;
                case YEONGDEUNGPO:
                    FacilityData<FacilityYeongdeungpo> yeongdeungpo = controller.fetchData(urlData.getNumber(), uuidData.getUuid());
                    data = yeongdeungpo;
                    log.info("============{} END=============",urlData);
                    latlngService.checkUrlData(urlData.getNumber(), data.getData());
                    break;
                case YONGSAN:
                    FacilityData<FacilityYongsan> yongsan = controller.fetchData(urlData.getNumber(), uuidData.getUuid());
                    data = yongsan;
                    log.info("============{} END=============",urlData);
                    latlngService.checkUrlData(urlData.getNumber(), data.getData());
                    break;
                case GURO:
                    FacilityData<FacilityGuro> guro = controller.fetchData(urlData.getNumber(), uuidData.getUuid());
                    data = guro;
                    log.info("============{} END=============",urlData);
                    latlngService.checkUrlData(urlData.getNumber(), data.getData());
                    break;
                case NOWON:
                    FacilityData<FacilityNowon> nowon = controller.fetchData(urlData.getNumber(), uuidData.getUuid());
                    data = nowon;
                    log.info("============{} END=============",urlData);
                    latlngService.checkUrlData(urlData.getNumber(), data.getData());
                    break;
                default:
                    controller.fetchData(urlData.getNumber(),uuidData.getUuid());
                    log.info("============{} END=============",urlData);
                    break;
            }
        }
        log.info("===============TotalDataReset=================");
        totalDataService.resetTotalData();
        log.info("===============TotalDataSave==============");
        totalDataService.setTotalData();
        log.info("==============NewDataSave================");
        //캐시 데이터 저장
        log.info("==============cacheDataSave================");
        responseService.getTotalData();
    }
}
