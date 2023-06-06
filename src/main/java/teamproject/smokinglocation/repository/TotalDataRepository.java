package teamproject.smokinglocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import teamproject.smokinglocation.entity.TotalData;

@Repository
public interface TotalDataRepository extends JpaRepository<TotalData, Long> {
    @Modifying
    @Query("DELETE FROM TotalData")
    void deleteAllData();
    @Modifying
    @Query(value = "ALTER SEQUENCE total_data_id_seq RESTART WITH 1", nativeQuery = true)
    void resetSequence();

}
