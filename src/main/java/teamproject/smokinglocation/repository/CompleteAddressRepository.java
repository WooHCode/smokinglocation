package teamproject.smokinglocation.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.entity.Gangbuk;
import teamproject.smokinglocation.entity.Junggu;
import teamproject.smokinglocation.entity.Jungnang;
import teamproject.smokinglocation.entity.Songpa;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CompleteAddressRepository {

    private final EntityManager em;

    public void saveGangbuk(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveGangbukData 실행===============");
        for (int i = 0; i < length; i++) {
            Gangbuk entity = Gangbuk.builder()
                    .gu("GANGBUK")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(entity);
        }

    }

    public void saveJungnangData(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveJungnangData 실행===============");
        for (int i = 0; i < length; i++) {
                Jungnang entity = Jungnang.builder()
                    .gu("JUNGNANG")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(entity);
        }

    }

    public void saveSongpaData(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveSongpaData 실행===============");

        for (int i = 0; i < length; i++) {
            Songpa entity = Songpa.builder()
                    .gu("SONGPA")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(entity);
        }
    }

    public void saveJungguData(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveJungguData 실행===============");

        for (int i = 0; i < length; i++) {
            Junggu entity = Junggu.builder()
                    .gu("JUNGGU")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(entity);
        }
    }
}
