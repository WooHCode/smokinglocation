package teamproject.smokinglocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamproject.smokinglocation.userEnitiy.SavedSpot;

import java.util.Map;

public interface SavedSpotRepository extends JpaRepository<SavedSpot, Long> {
}
