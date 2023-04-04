package teamproject.smokinglocation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import teamproject.smokinglocation.dto.Facility;
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
public class MapController {
    public static final String  secretKey = "hWIiF1x6ClYGmxA62SIpOR476d8h0BZg9BTK288BLaIuLINJEvFOKU1CGk%2BQKg8Jr2DrdCX2jKFpxDe44YTYuQ%3D%3D";

    private final ObjectMapper objectMapper;
    @Value("${naver.map.client.id}")
    private String naverMapClientId;

    @GetMapping("/map")
    public String showMap(Model model) {
        List<Facility> facilities = fetchData();
        model.addAttribute("facilities", facilities);

        model.addAttribute("naverMapClientId",naverMapClientId);
        return "map";
    }

    @GetMapping("/get-data")
    @ResponseBody
    public List<Facility> fetchData() {
        String apiUrl = "https://api.odcloud.kr/api/15073796/v1/uddi:17fbd06c-45bb-48aa-9be7-b26dbc708c9c" +
                "?serviceKey=" + secretKey;
        List<Facility> facilities = new ArrayList<>();

        try {
            URL url = new URL(apiUrl);
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

            FacilityData facilityData = objectMapper.readValue(jsonString, FacilityData.class);
            facilities.addAll(facilityData.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return facilities;
    }
}

