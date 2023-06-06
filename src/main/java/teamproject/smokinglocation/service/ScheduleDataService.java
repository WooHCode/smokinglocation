package teamproject.smokinglocation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.repository.ScheduleDataRepository;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ScheduleDataService {
    private final ScheduleDataRepository repository;

    @Transactional
    public void deleteOldData() {
        repository.DeleteDataDongjak();
        repository.DeleteDataDongdaemun();
        repository.DeleteDataGangbuk();
        repository.DeleteDataGangnam();
        repository.DeleteDataGangseo();
        repository.DeleteDataGuro();
        repository.DeleteDataGwanak();
        repository.DeleteDataGwangjin();
        repository.DeleteDataJongno();
        repository.DeleteDataJunggu();
        repository.DeleteDataJungnang();
        repository.DeleteDataNowon();
        repository.DeleteDataSeocho();
        repository.DeleteDataSeodaemun();
        repository.DeleteDataSeongbuk();
        repository.DeleteDataSeongdong();
        repository.DeleteDataSongpa();
        repository.DeleteDataYangcheon();
        repository.DeleteDataYeongdeungpo();
        repository.DeleteDataYongsan();
    }
}
