package teamproject.smokinglocation.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import teamproject.smokinglocation.entity.*;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UncompletedAddressRepository {

    private final EntityManager em;

    public void saveJongno(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveJongno 실행===============");
        for (int i = 0; i < length; i++) {
            Jongno jongno = Jongno.builder()
                    .gu("JONGNO")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(jongno);
        }

    }

    public void saveGangnam(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveGangnam 실행===============");
        for (int i = 0; i < length; i++) {
            Gangnam gangnam = Gangnam.builder()
                    .gu("GANGNAM")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(gangnam);
        }

    }

    public void saveSeongbuk(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveSeongbuk 실행===============");
        for (int i = 0; i < length; i++) {
            Seongbuk seongbuk = Seongbuk.builder()
                    .gu("SEONGBUK")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(seongbuk);
        }

    }

    public void saveSeongdong(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveSeongdong 실행===============");
        for (int i = 0; i < length; i++) {
            Seongdong seongdong = Seongdong.builder()
                    .gu("SEONGDONG")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(seongdong);
        }

    }

    public void saveDongdaemun(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveDongdaemun 실행===============");
        for (int i = 0; i < length; i++) {
            Dongdaemun dongdaemun = Dongdaemun.builder()
                    .gu("DONGDAEMUN")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(dongdaemun);
        }

    }
    public void saveGwanakData(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveGwanakData 실행===============");
        for (int i = 0; i < length; i++) {
            Gwanak entity = Gwanak.builder()
                    .gu("GWANAK")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(entity);
        }
    }

    public void saveYangcheonData(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveYangcheonData 실행===============");
        for (int i = 0; i < length; i++) {
            Yangcheon entity = Yangcheon.builder()
                    .gu("YANGCHEON")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(entity);
        }
    }

    public void saveSeodaemunData(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveSeodaemunData 실행===============");
        for (int i = 0; i < length; i++) {
            Seodaemun entity = Seodaemun.builder()
                    .gu("SEODAEMUN")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(entity);
        }
    }

    public void saveGangseoData(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveGangseoData 실행===============");
        for (int i = 0; i < length; i++) {
            Gangseo entity = Gangseo.builder()
                    .gu("GANGSEO")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(entity);
        }
    }

    public void saveGwangjinData(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveGwangjinData 실행===============");
        for (int i = 0; i < length; i++) {
            Gwangjin entity = Gwangjin.builder()
                    .gu("GWANGJIN")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(entity);
        }
    }

    public void saveSeochoData(List<String[]> latLng, List<String> addressList) {
        int length = latLng.size();
        log.info("=============saveSeochoData 실행===============");
        for (int i = 0; i < length; i++) {
            Seocho seocho = Seocho.builder()
                    .gu("SEOCHO")
                    .lat(latLng.get(i)[0])
                    .lon(latLng.get(i)[1])
                    .location(addressList.get(i))
                    .build();
            em.persist(seocho);
        }
    }
}
