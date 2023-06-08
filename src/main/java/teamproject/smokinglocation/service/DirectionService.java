package teamproject.smokinglocation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DirectionService {

    @Value("${naver.directions.client.id}")
    private String naverDirectionClientId;

    @Value("${naver.directions.client.secret}")
    private String naverDirectionSecret;

    public String getDirections(String start, String end) {
        System.out.println("=======getDirections : START");

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

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("=======getDirections : API 호출");
            return response.getBody();
        } else {
            // API 호출 실패 처리
            System.out.println("=======getDirections : API 호출 실패");
            return null;
        }
    }
}
