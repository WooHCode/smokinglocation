package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teamproject.smokinglocation.service.DirectionService;

@RestController
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService directionService;

    @GetMapping("/directions")
    public ResponseEntity<String> getDirections(
            @RequestParam("start") String start,
            @RequestParam("goal") String goal
    ) {
        String directions = directionService.getDirections(start, goal);

        if (directions != null) {
            System.out.println("=======DirectionController.getDirections : API 호출");
            return ResponseEntity.ok(directions);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
