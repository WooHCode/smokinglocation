package teamproject.smokinglocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamproject.smokinglocation.userEnitiy.SavedSpot;

public interface SpotRepository extends JpaRepository<SavedSpot,Long> {
}
