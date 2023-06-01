package teamproject.smokinglocation.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.entity.Dongjak;
import teamproject.smokinglocation.entity.Nowon;
import teamproject.smokinglocation.entity.Yeongdeungpo;
import teamproject.smokinglocation.entity.Yongsan;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LatlngRepository {
    private final EntityManager em;

    public void saveYongsan(String lat, String lon, String location) {
        Yongsan yongsan = Yongsan.builder()
                .gu("YONGSAN")
                .lat(lat)
                .lon(lon)
                .location(location)
                .build();
        em.persist(yongsan);
    }

    public void saveYeongdeungpo(String lat, String lon, String location) {
        Yeongdeungpo yeongdeungpo = Yeongdeungpo.builder()
                .gu("YEONGDEUNGPO")
                .lat(lat)
                .lon(lon)
                .location(location)
                .build();
        em.persist(yeongdeungpo);
    }
    public void saveDongjak(String lat, String lon, String location) {
        Dongjak dongjak = Dongjak.builder()
                .gu("DONGJAK")
                .lat(lat)
                .lon(lon)
                .location(location)
                .build();
        em.persist(dongjak);
    }
    public void saveGuro(String lat, String lon, String location) {
        Dongjak dongjak = Dongjak.builder()
                .gu("GURO")
                .lat(lat)
                .lon(lon)
                .location(location)
                .build();
        em.persist(dongjak);
    }

    public void saveNowon(String lat, String lon, String location) {
        Nowon nowon = Nowon.builder()
                .gu("NOWON")
                .lat(lat)
                .lon(lon)
                .location(location)
                .build();
        em.persist(nowon);
    }
}
