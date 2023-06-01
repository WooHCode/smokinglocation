package teamproject.smokinglocation.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DataResponseRepository {
    private final EntityManager em;

    public List<Object[]> findDataYongsan() {
        return em.createQuery("SELECT y.lat, y.lon, y.location, y.gu FROM Yongsan y").getResultList();
    }

    public List<Object[]> findDataDongdaemun() {
        return em.createQuery("SELECT d.lat, d.lon, d.location, d.gu FROM Dongdaemun d").getResultList();
    }

    public List<Object[]> findDataDongjak() {
        return em.createQuery("SELECT d.lat, d.lon, d.location, d.gu FROM Dongjak d").getResultList();
    }

    public List<Object[]> findDataGangbuk() {
        return em.createQuery("SELECT g.lat, g.lon, g.location, g.gu FROM  Gangbuk g").getResultList();
    }

    public List<Object[]> findDataGangnam() {
        return em.createQuery("SELECT g.lat, g.lon, g.location, g.gu FROM  Gangnam g").getResultList();
    }

    public List<Object[]> findDataGangseo() {
        return em.createQuery("SELECT g.lat, g.lon, g.location, g.gu FROM  Gangseo g").getResultList();
    }

    public List<Object[]> findDataGuro() {
        return em.createQuery("SELECT g.lat, g.lon, g.location, g.gu FROM  Guro g").getResultList();
    }

    public List<Object[]> findDataGwanak() {
        return em.createQuery("SELECT g.lat, g.lon, g.location, g.gu FROM  Gwanak g").getResultList();
    }

    public List<Object[]> findDataGwangjin() {
        return em.createQuery("SELECT g.lat, g.lon, g.location, g.gu FROM  Gwangjin g").getResultList();
    }

    public List<Object[]> findDataJongno() {
        return em.createQuery("SELECT j.lat, j.lon, j.location, j.gu FROM  Jongno j").getResultList();
    }

    public List<Object[]> findDataJunggu() {
        return em.createQuery("SELECT j.lat, j.lon, j.location, j.gu FROM  Junggu j").getResultList();
    }

    public List<Object[]> findDataJungnang() {
        return em.createQuery("SELECT j.lat, j.lon, j.location, j.gu FROM  Jungnang j").getResultList();
    }

    public List<Object[]> findDataNowon() {
        return em.createQuery("SELECT n.lat, n.lon, n.location, n.gu FROM  Nowon n").getResultList();
    }

    public List<Object[]> findDataSeocho() {
        return em.createQuery("SELECT s.lat, s.lon, s.location, s.gu FROM  Seocho s").getResultList();
    }

    public List<Object[]> findDataSeodaemun() {
        return em.createQuery("SELECT s.lat, s.lon, s.location, s.gu FROM  Seodaemun s").getResultList();
    }

    public List<Object[]> findDataSeongbuk() {
        return em.createQuery("SELECT s.lat, s.lon, s.location, s.gu FROM  Seongbuk s").getResultList();
    }

    public List<Object[]> findDataSeongdong() {
        return em.createQuery("SELECT s.lat, s.lon, s.location, s.gu FROM  Seongdong s").getResultList();
    }

    public List<Object[]> findDataSongpa() {
        return em.createQuery("SELECT s.lat, s.lon, s.location, s.gu FROM  Songpa s").getResultList();
    }

    public List<Object[]> findDataYangcheon() {
        return em.createQuery("SELECT y.lat, y.lon, y.location, y.gu FROM  Yangcheon y").getResultList();
    }

    public List<Object[]> findDataYeondDeungpo() {
        return em.createQuery("SELECT y.lat, y.lon, y.location, y.gu FROM Yeongdeungpo y").getResultList();
    }
}
