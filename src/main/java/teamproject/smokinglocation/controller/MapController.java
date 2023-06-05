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
import teamproject.smokinglocation.dto.*;
import teamproject.smokinglocation.entity.TotalData;
import teamproject.smokinglocation.service.DataResponseService;
import teamproject.smokinglocation.service.DataService;
import teamproject.smokinglocation.service.LatlngService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MapController {
    public static final String secretKey = "dP9ZxjdllWOjmt5zzJVCVHUHXysykcUWsLPHbnfHrf3JcOK2zEo60B3iOrILphRT5xRc865itVYu9KAlGGEyLw%3D%3D";
    public static final int perPage = 500;

    private final LatlngService latlngService;
    private final DataResponseService responseService;
    private final DataService dataService;
    private final ObjectMapper objectMapper;
    @Value("${naver.map.client.id}")

    private String naverMapClientId;
    @GetMapping("/map")
    public String showMap(Model model) {
        List<TotalData> totalData = responseService.getTotalData();
        log.info("===========totalDataLoading===========");
        log.info("totalData = {}", totalData);
        log.info("===========totalDataLoadingFinish============");
        model.addAttribute("facilities", totalData);
        model.addAttribute("naverMapClientId", naverMapClientId);
        return "map";
    }

    @GetMapping("/get-data")
    @ResponseBody
    public <T> FacilityData<T> fetchData(int urlData, String uuid) {
        String apiUrl = "https://api.odcloud.kr/api/" + urlData + "/v1/uddi:" + uuid +
                "?serviceKey=" + secretKey +"&perPage=" + perPage;
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
            dataService.saveLatLng(facilityData, urlData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return facilityData;
    }
}

