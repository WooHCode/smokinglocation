package teamproject.smokinglocation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teamproject.smokinglocation.dto.pathDataDto.PathData;
import teamproject.smokinglocation.dto.pathDataDto.Traoptimal;
import teamproject.smokinglocation.service.DirectionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService directionService;
    private final ObjectMapper objectMapper;

    @GetMapping("/directions")
    public ResponseEntity getDirections(
            @RequestParam("myLng") String myLng,
            @RequestParam("myLat") String myLat,
            @RequestParam("endLng") String endLng,
            @RequestParam("endLat") String endLat
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


        return ResponseEntity.ok(directions);
    }
}
