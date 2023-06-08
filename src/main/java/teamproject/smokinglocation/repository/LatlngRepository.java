package teamproject.smokinglocation.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.entity.*;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LatlngRepository {
    private final EntityManager em;

    public void saveYongsan(String lat, String lon, String location) {
        log.info("==========LatlngRepository.saveYongsan()===============");
        Yongsan yongsan = Yongsan.builder()
                .gu("YONGSAN")
                .lat(lat)
                .lon(lon)
                .location(location)
                .build();
        em.persist(yongsan);
    }

    public void saveYeongdeungpo(String lat, String lon, String location) {
        log.info("==========LatlngRepository.saveYeongdeungpo()===============");
        Yeongdeungpo yeongdeungpo = Yeongdeungpo.builder()
                .gu("YEONGDEUNGPO")
                .lat(lat)
                .lon(lon)
                .location(location)
                .build();
        em.persist(yeongdeungpo);
    }
    public void saveDongjak(String lat, String lon, String location) {
        log.info("==========LatlngRepository.saveDongjak()===============");
        Dongjak dongjak = Dongjak.builder()
                .gu("DONGJAK")
                .lat(lat)
                .lon(lon)
                .location(location)
                .build();
        em.persist(dongjak);
    }
    public void saveGuro(String lat, String lon, String location) {
        log.info("==========LatlngRepository.saveGuro()===============");
        Guro guro = Guro.builder()
                .gu("GURO")
                .lat(lat)
                .lon(lon)
                .location(location)
                .build();
        em.persist(guro);
    }

    public void saveNowon(String lat, String lon, String location) {
        log.info("==========LatlngRepository.saveNowon()===============");
        Nowon nowon = Nowon.builder()
                .gu("NOWON")
                .lat(lat)
                .lon(lon)
                .location(location)
                .build();
        em.persist(nowon);
    }
}
