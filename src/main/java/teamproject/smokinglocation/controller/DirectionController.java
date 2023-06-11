package teamproject.smokinglocation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
        System.out.println("myLng = " + myLng);
        System.out.println("myLat = " + myLat);
        System.out.println("endLng = " + endLng);
        System.out.println("endLat = " + endLat);

        String myLocation = myLng + "," + myLat;
        String endLocation = endLng + "," + endLat;
        String directions = directionService.getDirections(myLocation, endLocation);
        System.out.println("=======DirectionController 실행완료");
        PathData pathData = new PathData();
        pathData = objectMapper.readValue(directions, new TypeReference<>() {});

        System.out.println("==========pathData 추출 시작==========");
        List<List<Float>> pathFromJson = pathData.getRoute().getTraoptimal().get(0).getPath();
        System.out.println(pathFromJson);
        List<PathSpot> pathSpots = new ArrayList<>();
        for (List<Float> pathSpot : pathFromJson) {
            PathSpot pathSpotDto = new PathSpot();
            pathSpotDto.setLng(pathSpot.get(0));
            pathSpotDto.setLat(pathSpot.get(1));
            pathSpots.add(pathSpotDto);
        }
        System.out.println("==========pathData 추출 완료==========");

        return pathSpots;
    }
}
