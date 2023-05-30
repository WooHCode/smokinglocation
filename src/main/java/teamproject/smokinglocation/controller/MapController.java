package teamproject.smokinglocation.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
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
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MapController {
    public static final String secretKey = "dP9ZxjdllWOjmt5zzJVCVHUHXysykcUWsLPHbnfHrf3JcOK2zEo60B3iOrILphRT5xRc865itVYu9KAlGGEyLw%3D%3D";

    private final ObjectMapper objectMapper;
    @Value("${naver.map.client.id}")
    private String naverMapClientId;

    @GetMapping("/map")
    public String showMap(Model model) {
        FacilityData<FacilityYongsan> facilityDataYongsan = fetchData(UrlData.YONGSAN.getNumber(), UuidData.YONGSAN.getUuid());
        List<FacilityYongsan> yongsanFacilities = facilityDataYongsan.getData();

        // FacilityYongsan 데이터 사용
        model.addAttribute("facilities", yongsanFacilities);
        model.addAttribute("naverMapClientId", naverMapClientId);
        return "map";
    }

    @GetMapping("/get-data")
    @ResponseBody
    public <T> FacilityData<T> fetchData(int urlData, String uuid) {
        String apiUrl = "https://api.odcloud.kr/api/" + urlData + "/v1/uddi:" + uuid +
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

    /**
     * 성북구의 데이터를 파싱하여 위도 경도를 알아내는 메서드
     * 다른 구의 위도 경도 데이터가 없다면 해당 메서드를 구 별로 작성할 필요 있음.
     * @return
     */
    public JsonArray getSeongBukData() {
    //TODO 해당 값들을 DB에 저장
        String districtName;
        String manageFacility;
        FacilityData<Object> data = fetchData(UrlData.SEONGBUK.getNumber(), UuidData.SEONGBUK.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }

        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("자치구");
            manageFacility = linkedHashMap.get("관리");
            addressList.add(districtName+manageFacility);
        }
        JsonArray result = getJsonElements(addressList);
        return result;
    }



    /**
     * 종로구의 위도 경도 데이터를 받아오는 메서드
     * @return
     */
    public JsonArray getJongnoData() {

        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 종로구 ";
        FacilityData<Object> data = fetchData(UrlData.JONGNO.getNumber(), UuidData.JONGNO.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치장소");
            addressList.add(gu+districtName);
        }
        JsonArray result = getJsonElements(addressList);
        return result;
    }

    public JsonArray getGangnamData() {

        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 강남구 ";
        FacilityData<Object> data = fetchData(UrlData.GANGNAM.getNumber(), UuidData.GANGNAM.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치주소");
            addressList.add(gu+districtName);
        }
        JsonArray result = getJsonElements(addressList);
        return result;
    }

    public JsonArray getSeochoData() {
        //TODO 해당내용 DB에 저장
        //해당메서드 사용불가능, 직접 파싱해서 db에 저장해야함.
        String districtName;
        String gu = "서울시 서초구 ";
        FacilityData<Object> data = fetchData(UrlData.GANGNAM.getNumber(), UuidData.GANGNAM.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치주소");
            addressList.add(gu+districtName);
        }
        JsonArray result = getJsonElements(addressList);
        return result;
    }

    public JsonArray getSeongDongData() {
        //TODO 해당내용 DB에 저장
        //해당메서드 사용불가능, 직접 파싱해서 db에 저장해야함.
        String districtName;
        String gu = "서울시 성동구 ";
        FacilityData<Object> data = fetchData(UrlData.SEONGDONG.getNumber(), UuidData.SEONGDONG.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치위치");
            addressList.add(gu+districtName);
        }
        JsonArray result = getJsonElements(addressList);
        return result;
    }

    public JsonArray getDongDaeMunData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 동대문구 ";
        FacilityData<Object> data = fetchData(UrlData.DONGDAEMUN.getNumber(), UuidData.DONGDAEMUN.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치위치");
            addressList.add(gu+districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return result;
    }

    public JsonArray getGwanakData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 관악구 ";
        FacilityData<Object> data = fetchData(UrlData.GWANAK.getNumber(), UuidData.GWANAK.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치위치");
            addressList.add(gu+districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return result;
    }

    public JsonArray getYangCheonData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 양천구 ";
        FacilityData<Object> data = fetchData(UrlData.YANGCHEON.getNumber(), UuidData.YANGCHEON.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치기관");
            addressList.add(gu+districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return result;
    }

    public JsonArray getSeodaemunData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 서대문구 ";
        FacilityData<Object> data = fetchData(UrlData.SEODAEMUN.getNumber(), UuidData.SEODAEMUN.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치위치");
            addressList.add(gu+districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return result;
    }
    public JsonArray getGangseoData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 강서구 ";
        FacilityData<Object> data = fetchData(UrlData.GANGSEO.getNumber(), UuidData.GANGSEO.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치 위치");
            addressList.add(gu+districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return result;
    }

    public JsonArray getGwanjinData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 광진구 ";
        FacilityData<Object> data = fetchData(UrlData.GWANGJIN.getNumber(), UuidData.GWANGJIN.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("영업소소재지(도로 명)");
            addressList.add(gu+districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return result;
    }

    //========================전체 주소가 있는 자치구========================

    public JsonArray getGangbukData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        FacilityData<Object> data = fetchData(UrlData.GANGBUK.getNumber(), UuidData.GANGBUK.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("주소");
            addressList.add(districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return result;
    }

    public JsonArray getJungnangData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        FacilityData<Object> data = fetchData(UrlData.JUNGNANG.getNumber(), UuidData.JUNGNANG.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("주소");
            addressList.add(districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return result;
    }


    public JsonArray getSongpaData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        FacilityData<Object> data = fetchData(UrlData.SONGPA.getNumber(), UuidData.SONGPA.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("도로명주소");
            addressList.add(districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return result;
    }

    public JsonArray getJungguData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        FacilityData<Object> data = fetchData(UrlData.JUNGGU.getNumber(), UuidData.JUNGGU.getUuid());

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }
        // dataList에는 전체 요소가 LinkedHashMap<String, String> 타입으로 캐스팅되어 저장됨

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치도로명주소");
            addressList.add(districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return result;
    }


    private static JsonArray getJsonElements(List<String> addressList) {
        JsonArray result = new JsonArray();
        for (String address : addressList){
            try {
                address = URLEncoder.encode(address, "UTF-8");
                String apiKey = "AIzaSyCNyoU8SQu02cSwnu4xq6i1N1rGs5o1WIk";
                String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + apiKey;
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    String responseBody = response.body();
                    Gson gson = new Gson();
                    JsonElement jsonElement = gson.fromJson(responseBody, JsonElement.class);
                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                    if (jsonObject.has("status") && jsonObject.get("status").getAsString().equals("ZERO_RESULTS")) {
                        result.add("no data");
                    }else {
                        JsonElement results = jsonObject.getAsJsonArray("results").get(0);
                        JsonObject results1 = results.getAsJsonObject();
                        JsonObject geometry = results1.getAsJsonObject("geometry");

                        JsonObject location = geometry.getAsJsonObject("location");
                        result.add(location);
                    }

                } else {
                    System.out.println("Geocoding API request failed. Status code: " + response.statusCode());
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

