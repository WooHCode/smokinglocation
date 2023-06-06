package teamproject.smokinglocation.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class ScheduleDataRepository {

    private final EntityManager em;

    public void DeleteDataDongjak() {
        String sequenceName = "dongjak_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Dongjak d");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }

    public void DeleteDataDongdaemun() {
        String sequenceName = "dongdaemun_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Dongdaemun d");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataGangbuk() {
        String sequenceName = "gangbuk_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Gangbuk g");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataGangnam() {
        String sequenceName = "gangnam_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Gangnam g");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataGangseo() {
        String sequenceName = "gangseo_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Gangseo g");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataGuro() {
        String sequenceName = "guro_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Guro g");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataGwanak() {
        String sequenceName = "gwanak_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Gwanak g");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataGwangjin() {
        String sequenceName = "gwangjin_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Gwangjin g");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataJongno() {
        String sequenceName = "jongno_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Jongno j");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataJunggu() {
        String sequenceName = "junggu_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Junggu j");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataJungnang() {
        String sequenceName = "jungnang_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Jungnang j");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataNowon() {
        String sequenceName = "nowon_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Nowon n");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataSeocho() {
        String sequenceName = "seocho_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Seocho s");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataSeodaemun() {
        String sequenceName = "seodaemun_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Seodaemun s");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataSeongbuk() {
        String sequenceName = "seongbuk_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Seongbuk s");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataSeongdong() {
        String sequenceName = "seongdong_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Seongdong s");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataSongpa() {
        String sequenceName = "songpa_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Songpa s");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataYangcheon() {
        String sequenceName = "yangcheon_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Yangcheon y");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataYeongdeungpo() {
        String sequenceName = "yeongdeungpo_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Yeongdeungpo y");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
    public void DeleteDataYongsan() {
        String sequenceName = "yongsan_id_seq";
        Query deleteData = em.createQuery("DELETE FROM Yongsan y");
        String resetSequenceQuery = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
        Query sequenceQuery = em.createNativeQuery(resetSequenceQuery);

        deleteData.executeUpdate();
        sequenceQuery.executeUpdate();
    }
}
