package teamproject.smokinglocation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class DirectionService {

    @Value("${naver.directions.client.id}")
    private String naverDirectionClientId;

    @Value("${naver.directions.client.secret}")
    private String naverDirectionSecret;

    public String getDirections(String start, String end) {
        log.info("=======DirectionService.getDirections : START");

        String apiUrl = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving";

        // 요청 URL에 파라미터 추가
        String requestUrl = apiUrl + "?start=" + start + "&goal=" + end;

        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", naverDirectionClientId);
        headers.set("X-NCP-APIGW-API-KEY", naverDirectionSecret);

        // HTTP 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        log.info("=======DirectionService.getDirections : HTTP 요청 DONE");

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("=======DirectionService.getDirections : API 호출");
            log.info("=======DirectionService.getDirections : response.getBody() = {}", response.getBody());
            log.info("=======DirectionService.getDirections : END");

            return response.getBody();
        } else {
            // API 호출 실패 처리
            log.info("=======DirectionService.getDirections : API 호출 실패");
            return null;
        }
    }
}
