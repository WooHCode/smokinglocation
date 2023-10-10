package teamproject.smokinglocation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.repository.SavedSpotRepository;
import teamproject.smokinglocation.userEnitiy.SavedSpot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SavedSpotService {
    private final SavedSpotRepository savedSpotRepository;

    @Transactional
    public String[] getLngLatById(Long id) {
        log.info("======SavedSpotService.getLngLatById() START==============");
        SavedSpot savedSpot = savedSpotRepository.findById(id).orElseThrow();
        String[] lngLat = new String[3];
        lngLat[0] = savedSpot.getLng();
        lngLat[1] = savedSpot.getLat();
        lngLat[2] = savedSpot.getLoc();
        log.info("SavedSpot의 Lng={}, Lat={}, Loc={}", lngLat[0],lngLat[1],lngLat[2]);
        log.info("======SavedSpotService.getLngLatById() END==============");
        return lngLat;
    }

    @Transactional
    public void deleteSavedSpot(Long id) {
        savedSpotRepository.deleteById(id);
    }
}
