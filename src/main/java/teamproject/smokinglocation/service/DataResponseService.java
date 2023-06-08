package teamproject.smokinglocation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.entity.*;
import teamproject.smokinglocation.repository.DataResponseRepository;
import teamproject.smokinglocation.repository.TotalDataRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@EnableCaching
public class DataResponseService {
    private final DataResponseRepository responseRepository;
    private final TotalDataRepository dataRepository;
    private final CacheManager cacheManager;

    @Cacheable("totalDataCache")
    public List<TotalData> getTotalData() {
       return dataRepository.findAll();
    }
    @CacheEvict("totalDataCache")
    public void deleteTotalDataCache() {
        Cache cache = cacheManager.getCache("totalDataCache");
        if (cache != null) {
            log.info("=============DELETE CACHE DATA ({})===============",cache);
            cache.clear();
        }
    }

    public List<Gangnam> getGangnamData() {
        List<Gangnam> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataGangnam();
        for (Object[] row : dataList) {
            Gangnam data = Gangnam.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }
    public List<Gangbuk> getGangbukData() {
        List<Gangbuk> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataGangbuk();
        for (Object[] row : dataList) {
            Gangbuk data = Gangbuk.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Gangseo> getGangseoData() {
        List<Gangseo> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataGangseo();
        for (Object[] row : dataList) {
            Gangseo data = Gangseo.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Gwanak> getGwanakData() {
        List<Gwanak> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataGwanak();
        for (Object[] row : dataList) {
            Gwanak data = Gwanak.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Gwangjin> getGwangjinData() {
        List<Gwangjin> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataGwangjin();
        for (Object[] row : dataList) {
            Gwangjin data = Gwangjin.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Guro> getGuroData() {
        List<Guro> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataGuro();
        for (Object[] row : dataList) {
            Guro data = Guro.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Nowon> getNowonData() {
        List<Nowon> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataNowon();
        for (Object[] row : dataList) {
            Nowon data = Nowon.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Dongdaemun> getDongdaemunData() {
        List<Dongdaemun> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataDongdaemun();
        for (Object[] row : dataList) {
            Dongdaemun data = Dongdaemun.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Dongjak> getDongjakData() {
        List<Dongjak> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataDongjak();
        for (Object[] row : dataList) {
            Dongjak data = Dongjak.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Seodaemun> getSeodaemunData() {
        List<Seodaemun> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataSeodaemun();
        for (Object[] row : dataList) {
            Seodaemun data = Seodaemun.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Seocho> getSeochoData() {
        List<Seocho> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataSeocho();
        for (Object[] row : dataList) {
            Seocho data = Seocho.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Seongdong> getSeongdongData() {
        List<Seongdong> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataSeongdong();
        for (Object[] row : dataList) {
            Seongdong data = Seongdong.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Seongbuk> getSeongbukData() {
        List<Seongbuk> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataSeongbuk();
        for (Object[] row : dataList) {
            Seongbuk data = Seongbuk.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Songpa> getSongpaData() {
        List<Songpa> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataSongpa();
        for (Object[] row : dataList) {
            Songpa data = Songpa.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Yangcheon> getYangcheonData() {
        List<Yangcheon> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataYangcheon();
        for (Object[] row : dataList) {
            Yangcheon data = Yangcheon.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Yeongdeungpo> getYeongdeungpoData() {
        List<Yeongdeungpo> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataYeondDeungpo();
        for (Object[] row : dataList) {
            Yeongdeungpo data = Yeongdeungpo.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Yongsan> getYongsanData() {
        List<Yongsan> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataYongsan();
        for (Object[] row : dataList) {
            Yongsan data = Yongsan.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Jongno> getJongnoData() {
        List<Jongno> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataJongno();
        for (Object[] row : dataList) {
            Jongno data = Jongno.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Junggu> getJungguData() {
        List<Junggu> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataJunggu();
        for (Object[] row : dataList) {
            Junggu data = Junggu.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }

    public List<Jungnang> getJungnangData() {
        List<Jungnang> guData = new ArrayList<>();
        List<Object[]> dataList = responseRepository.findDataJungnang();
        for (Object[] row : dataList) {
            Jungnang data = Jungnang.builder()
                    .lat((String) row[0])
                    .lon((String) row[1])
                    .location((String) row[2])
                    .gu((String) row[3])
                    .build();
            guData.add(data);
        }
        return guData;
    }
}
