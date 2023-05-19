package teamproject.smokinglocation.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import teamproject.smokinglocation.dto.FacilityYongsan;
import teamproject.smokinglocation.dto.FacilityData;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MapController {
    public static final String  secretKey = "hWIiF1x6ClYGmxA62SIpOR476d8h0BZg9BTK288BLaIuLINJEvFOKU1CGk%2BQKg8Jr2DrdCX2jKFpxDe44YTYuQ%3D%3D";

    private final ObjectMapper objectMapper;
    @Value("${naver.map.client.id}")
    private String naverMapClientId;

    @GetMapping("/map")
    public String showMap(Model model) {
        FacilityData<FacilityYongsan> facilityDataYongsan = fetchData();
        List<FacilityYongsan> yongsanFacilities = facilityDataYongsan.getData();
        // FacilityYongsan 데이터 사용
        model.addAttribute("facilities", yongsanFacilities);
        model.addAttribute("naverMapClientId",naverMapClientId);
        return "map";
    }

    @GetMapping("/get-data")
    @ResponseBody
    public <T> FacilityData<T> fetchData() {
        String apiUrl = "https://api.odcloud.kr/api/15073796/v1/uddi:17fbd06c-45bb-48aa-9be7-b26dbc708c9c" +
                "?serviceKey=" + secretKey;
        FacilityData<T> facilityData = new FacilityData<>();

        try {
            URL url = new URL(apiUrl);
            log.info("=============fetch start==============");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            conn.disconnect();

            String jsonString = response.toString();

            facilityData = objectMapper.readValue(jsonString, new TypeReference<>() {
            });
            log.info("facilityData = {}", facilityData);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return facilityData;
    }

}

