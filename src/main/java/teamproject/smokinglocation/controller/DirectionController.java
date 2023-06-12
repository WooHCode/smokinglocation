package teamproject.smokinglocation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teamproject.smokinglocation.dto.pathDataDto.PathData;
import teamproject.smokinglocation.dto.pathDataDto.PathSpot;
import teamproject.smokinglocation.service.DirectionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DirectionController {

    private final DirectionService directionService;
    private final ObjectMapper objectMapper;

    @GetMapping("/directions")
    public List<PathSpot> getDirections(
            @RequestParam("myLng") String myLng,
            @RequestParam("myLat") String myLat,
            @RequestParam("endLng") String endLng,
            @RequestParam("endLat") String endLat,
            Model model
    ) throws JsonProcessingException {
        log.info("=======DirectionController.getDirections : START");

        log.info("myLng = {}", myLng);
        log.info("myLat = {} ", myLat);
        log.info("endLng = {}", endLng);
        log.info("endLat = {}", endLat);

        String myLocation = myLng + "," + myLat;
        String endLocation = endLng + "," + endLat;

        String directions = directionService.getDirections(myLocation, endLocation);
        PathData pathData;
        pathData = objectMapper.readValue(directions, new TypeReference<>() {});

        log.info("=======DirectionController.getDirections : pathData 추출 시작==========");
        List<List<Float>> pathFromJson = pathData.getRoute().getTraoptimal().get(0).getPath();
        log.info(pathFromJson.toString());
        List<PathSpot> pathSpots = new ArrayList<>();
        for (List<Float> pathSpot : pathFromJson) {
            PathSpot pathSpotDto = new PathSpot();
            pathSpotDto.setLng(String.valueOf(pathSpot.get(0)));
            pathSpotDto.setLat(String.valueOf(pathSpot.get(1)));
            pathSpots.add(pathSpotDto);
        }
        log.info("=======DirectionController.getDirections : pathData 추출 완료==========");
        log.info("=======DirectionController.getDirections : END");
        return pathSpots;
    }
}
