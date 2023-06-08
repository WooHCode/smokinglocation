package teamproject.smokinglocation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.entity.TotalData;
import teamproject.smokinglocation.repository.DataResponseRepository;
import teamproject.smokinglocation.repository.TotalDataRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TotalDataService {
    private final DataResponseRepository repository;
    private final TotalDataRepository dataRepository;

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

    /**
     * totalData 테이블 리셋
     */
    public void resetTotalData() {
        dataRepository.deleteAllData();
        dataRepository.resetSequence();
    }
}
