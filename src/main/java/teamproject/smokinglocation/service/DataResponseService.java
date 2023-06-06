package teamproject.smokinglocation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Transactional
@EnableCaching
public class DataResponseService {
    private final DataResponseRepository repository;
    private final TotalDataRepository dataRepository;

    @Cacheable("totalDataCache")
    public List<TotalData> getTotalData() {
       return dataRepository.findAll();
    }

    public List<Gangnam> getGangnamData() {
        List<Gangnam> guData = new ArrayList<>();
        List<Object[]> dataList = repository.findDataGangnam();
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
        List<Object[]> dataList = repository.findDataGangbuk();
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
        List<Object[]> dataList = repository.findDataGangseo();
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
        List<Object[]> dataList = repository.findDataGwanak();
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
        List<Object[]> dataList = repository.findDataGwangjin();
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
        List<Object[]> dataList = repository.findDataGuro();
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
        List<Object[]> dataList = repository.findDataNowon();
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
        List<Object[]> dataList = repository.findDataDongdaemun();
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
        List<Object[]> dataList = repository.findDataDongjak();
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
        List<Object[]> dataList = repository.findDataSeodaemun();
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
        List<Object[]> dataList = repository.findDataSeocho();
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
        List<Object[]> dataList = repository.findDataSeongdong();
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
        List<Object[]> dataList = repository.findDataSeongbuk();
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
        List<Object[]> dataList = repository.findDataSongpa();
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
        List<Object[]> dataList = repository.findDataYangcheon();
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
        List<Object[]> dataList = repository.findDataYeondDeungpo();
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
        List<Object[]> dataList = repository.findDataYongsan();
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
        List<Object[]> dataList = repository.findDataJongno();
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
        List<Object[]> dataList = repository.findDataJunggu();
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
        List<Object[]> dataList = repository.findDataJungnang();
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


    /**
     * 전체 데이터를 호출하여 TotalData 테이블에 저장하는 메서드
     */
    public void setTotalData() {
        List<Object[]> dataYongsan = repository.findDataYongsan();
        List<Object[]> dataDongdaemun = repository.findDataDongdaemun();
        List<Object[]> dataDongjak = repository.findDataDongjak();
        List<Object[]> dataGangbuk = repository.findDataGangbuk();
        List<Object[]> dataGangnam = repository.findDataGangnam();
        List<Object[]> dataGangseo = repository.findDataGangseo();
        List<Object[]> dataGuro = repository.findDataGuro();
        List<Object[]> dataGwanak = repository.findDataGwanak();
        List<Object[]> dataGwangjin = repository.findDataGwangjin();
        List<Object[]> dataJongno = repository.findDataJongno();
        List<Object[]> dataJunggu = repository.findDataJunggu();
        List<Object[]> dataJungnang = repository.findDataJungnang();
        List<Object[]> dataNowon = repository.findDataNowon();
        List<Object[]> dataSeocho = repository.findDataSeocho();
        List<Object[]> dataSeodaemun = repository.findDataSeodaemun();
        List<Object[]> dataSeongbuk = repository.findDataSeongbuk();
        List<Object[]> dataSeongdong = repository.findDataSeongdong();
        List<Object[]> dataSongpa = repository.findDataSongpa();
        List<Object[]> dataYangcheon = repository.findDataYangcheon();
        List<Object[]> dataYeondDeungpo = repository.findDataYeondDeungpo();

        List<List<Object[]>> totalData = new ArrayList<>();
        totalData.add(dataYongsan);
        totalData.add(dataDongdaemun);
        totalData.add(dataDongjak);
        totalData.add(dataGangbuk);
        totalData.add(dataGangnam);
        totalData.add(dataGangseo);
        totalData.add(dataGuro);
        totalData.add(dataGwanak);
        totalData.add(dataGwangjin);
        totalData.add(dataJongno);
        totalData.add(dataJunggu);
        totalData.add(dataJungnang);
        totalData.add(dataNowon);
        totalData.add(dataSeocho);
        totalData.add(dataSeodaemun);
        totalData.add(dataSeongbuk);
        totalData.add(dataSeongdong);
        totalData.add(dataSongpa);
        totalData.add(dataYangcheon);
        totalData.add(dataYeondDeungpo);

        List<TotalData> result = new ArrayList<>();
        for (List<Object[]> totalDatum : totalData) {
            for (Object[] row : totalDatum) {
                String lat = (String) row[0];
                String lon = (String) row[1];
                String location = (String) row[2];
                String gu = (String) row[3];
                TotalData data = new TotalData(lat, lon, location, gu);
                result.add(data);
            }
        }
        dataRepository.saveAll(result);
    }


}
