package teamproject.smokinglocation.service;

import org.springframework.stereotype.Service;
import teamproject.smokinglocation.dto.NearbyLocationDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationNearbyService {
    public List<NearbyLocationDto> extractNearbyLocations(String lat, String lon, List<NearbyLocationDto> nearbyDtos) {
        List<NearbyLocationDto> nearbyLocationDtos = new ArrayList<>();

        for (NearbyLocationDto nearbyDto : nearbyDtos) {
            if (nearbyDto.getLatitude() != null && nearbyDto.getLongitude() != null &&
                    !nearbyDto.getLatitude().equals("null") && !nearbyDto.getLongitude().equals("null")) {
                double distance = calculateDistance(
                        Double.parseDouble(lat), Double.parseDouble(lon),
                        Double.parseDouble(nearbyDto.getLatitude()),
                        Double.parseDouble(nearbyDto.getLongitude()));

                if (distance <= 0.5) {
                    nearbyLocationDtos.add(nearbyDto);
                }
            }
        }
        return nearbyLocationDtos;
    }

    /**
     * 헤버시닝 공식을 이용하여 두 지점 사이의 거리를 계산
     */
    public static double calculateDistance(double myLat, double myLon, double targetLat, double targetLon) {
        double earthRadius = 6371; // 지구의 반지름 (단위 : km)

        double dLat = Math.toRadians(targetLat - myLat);
        double dLon = Math.toRadians(targetLon - myLon);

        double tmp = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(myLat)) * Math.cos(Math.toRadians(targetLat)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double temp = 2 * Math.atan2(Math.sqrt(tmp) , Math.sqrt(1 - tmp));

        double distance = earthRadius * temp; // 두 지점 사이의 거리 (단위: km)
        return distance;
    }
}
