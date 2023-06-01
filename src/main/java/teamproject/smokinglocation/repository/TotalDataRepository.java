package teamproject.smokinglocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamproject.smokinglocation.entity.TotalData;

@Repository
public interface TotalDataRepository extends JpaRepository<TotalData, Long> {
}
