package teamproject.smokinglocation.service;

import com.google.gson.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teamproject.smokinglocation.controller.UrlData;
import teamproject.smokinglocation.dto.FacilityData;
import teamproject.smokinglocation.repository.CompleteAddressRepository;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService<T> {

    private static FacilityData<Object> facilityData;

    private final CompleteAddressRepository completeAddressRepository;

    public void saveLatLng(FacilityData<T> facilityData, int urlData) {

        this.facilityData = (FacilityData<Object>) facilityData;

        List<String[]> latLngRes = checkUrl(urlData);
        if (latLngRes != null) {
            //TODO latLngRes값이 null이 아닐경우 return 값을 합쳐주는 로직생성
            try {
                for (String[] latLngRe : latLngRes) {
                    for (String s : latLngRe) {
                        System.out.println("s = " + s);
                    }
                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }


    }

    private List<String[]> checkUrl(int urlData) {
        if (urlData == UrlData.SEONGBUK.getNumber()) {
            return getSeongBukData();
        } else if (urlData == UrlData.JONGNO.getNumber()) {
            return getJongnoData();
        } else if (urlData == UrlData.GANGNAM.getNumber()) {
            return getGangnamData();
        } else if (urlData == UrlData.SEOCHO.getNumber()) {
            return getSeochoData();
        } else if (urlData == UrlData.SEONGDONG.getNumber()) {
            return getSeongDongData();
        } else if (urlData == UrlData.DONGDAEMUN.getNumber()) {
            return getDongDaeMunData();
        } else if (urlData == UrlData.GWANAK.getNumber()) {
            return getGwanakData();
        } else if (urlData == UrlData.YANGCHEON.getNumber()) {
            return getYangCheonData();
        } else if (urlData == UrlData.SEODAEMUN.getNumber()) {
            return getSeodaemunData();
        } else if (urlData == UrlData.GANGSEO.getNumber()) {
            return getGangseoData();
        } else if (urlData == UrlData.GWANGJIN.getNumber()) {
            return getGwangjinData();
        } else if (urlData == UrlData.GANGBUK.getNumber()) {
            return getGangbukData();
        } else if (urlData == UrlData.JUNGNANG.getNumber()) {
            return getJungnangData();
        } else if (urlData == UrlData.SONGPA.getNumber()) {
            return getSongpaData();
        } else if (urlData == UrlData.JUNGGU.getNumber()) {
            return getJungguData();
        } else return null;
    }


    /**
     * 성북구의 데이터를 파싱하여 위도 경도를 알아내는 메서드
     * 다른 구의 위도 경도 데이터가 없다면 해당 메서드를 구 별로 작성할 필요 있음.
     *
     * @return
     */
    public List<String[]> getSeongBukData() {
        //TODO 해당 값들을 DB에 저장

        String districtName;
        String manageFacility;
        FacilityData<Object> data = facilityData;

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
            addressList.add(districtName + manageFacility);
        }
        JsonArray result = getJsonElements(addressList);
        List<String[]> latLngList = getLatLng(result);
        for (String[] strings : latLngList) {
            System.out.println("Strings = " + strings[0] + "/" + strings[1]);
        }
        return latLngList;
    }


    /**
     * 종로구의 위도 경도 데이터를 받아오는 메서드
     *
     * @return
     */
    public List<String[]> getJongnoData() {

        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 종로구 ";
        FacilityData<Object> data = facilityData;

        List<LinkedHashMap<String, String>> dataList = new ArrayList<>();

        for (Object item : data.getData()) {
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) item;
            dataList.add(linkedHashMap);
        }

        // 예시로 첫 번째 요소의 자치구와 관리 출력
        List<String> addressList = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : dataList) {
            districtName = linkedHashMap.get("설치장소");
            addressList.add(gu + districtName);
        }
        JsonArray result = getJsonElements(addressList);
        return getLatLng(result);
    }

    public List<String[]> getGangnamData() {

        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 강남구 ";
        FacilityData<Object> data = facilityData;

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
            addressList.add(gu + districtName);
        }
        JsonArray result = getJsonElements(addressList);
        return getLatLng(result);
    }

    public List<String[]> getSeochoData() {
        //TODO 해당내용 DB에 저장
        //해당메서드 사용불가능, 직접 파싱해서 db에 저장해야함.
        String districtName;
        String gu = "서울시 서초구 ";
        FacilityData<Object> data = facilityData;

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
            addressList.add(gu + districtName);
        }
        JsonArray result = getJsonElements(addressList);
        return getLatLng(result);
    }

    public List<String[]> getSeongDongData() {
        //TODO 해당내용 DB에 저장
        //해당메서드 사용불가능, 직접 파싱해서 db에 저장해야함.
        String districtName;
        String gu = "서울시 성동구 ";
        FacilityData<Object> data = facilityData;

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
            addressList.add(gu + districtName);
        }
        JsonArray result = getJsonElements(addressList);
        return getLatLng(result);
    }

    public List<String[]> getDongDaeMunData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 동대문구 ";
        FacilityData<Object> data = facilityData;

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
            addressList.add(gu + districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return getLatLng(result);
    }

    public List<String[]> getGwanakData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 관악구 ";
        FacilityData<Object> data = facilityData;

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
            addressList.add(gu + districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return getLatLng(result);
    }

    public List<String[]> getYangCheonData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 양천구 ";
        FacilityData<Object> data = facilityData;

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
            addressList.add(gu + districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return getLatLng(result);
    }

    public List<String[]> getSeodaemunData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 서대문구 ";
        FacilityData<Object> data = facilityData;

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
            addressList.add(gu + districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return getLatLng(result);
    }

    public List<String[]> getGangseoData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 강서구 ";
        FacilityData<Object> data = facilityData;

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
            addressList.add(gu + districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return getLatLng(result);
    }

    public List<String[]> getGwangjinData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        String gu = "서울시 광진구 ";
        FacilityData<Object> data = facilityData;

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
            addressList.add(gu + districtName);
        }
        JsonArray result = getJsonElements(addressList);
        for (JsonElement jsonElement : result) {
            System.out.println("jsonElement = " + jsonElement);
        }
        return getLatLng(result);
    }

    //========================전체 주소가 있는 자치구========================

    public List<String[]> getGangbukData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        FacilityData<Object> data = facilityData;

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
        completeAddressRepository.saveGangbuk(getLatLng(result),addressList);
        return getLatLng(result);
    }

    public List<String[]> getJungnangData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        FacilityData<Object> data = facilityData;

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
        completeAddressRepository.saveJungnangData(getLatLng(result), addressList);
        return getLatLng(result);
    }


    public List<String[]> getSongpaData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        FacilityData<Object> data = facilityData;

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
        completeAddressRepository.saveSongpaData(getLatLng(result), addressList);
        return getLatLng(result);
    }

    public List<String[]> getJungguData() {
        //TODO 해당내용 DB에 저장
        String districtName;
        FacilityData<Object> data = facilityData;

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
        completeAddressRepository.saveJungguData(getLatLng(result),addressList);
        return getLatLng(result);
    }

    private static JsonArray getJsonElements(List<String> addressList) {
        JsonArray result = new JsonArray();
        for (String address : addressList) {
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
                        /**
                         * 위도 경도 없을 때 문자열 "null" 넣어줌으로써 NPE 방지
                         */
                        String jsonString = "{\"lat\": null, \"lng\": null}";
                        JsonElement elt = new Gson().fromJson(jsonString, JsonElement.class);
                        result.add(elt);
                    } else {
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

    private static List<String[]> getLatLng(JsonArray result) {

        List<String[]> resultList = new ArrayList<>();
        for (JsonElement elt : result) {
            JsonObject jsonObject = JsonParser.parseString(elt.toString()).getAsJsonObject();
            String[] res = new String[2];

            System.out.println("jsonObject.get(\"lat\") = " + jsonObject.get("lat"));
            System.out.println("jsonObject.get(\"lng\") = " + jsonObject.get("lng"));

            if (jsonObject.get("lat").toString().equals("null")) {
                res[0] = "null";
            } else {
                res[0] = jsonObject.get("lat").toString();
            }

            if (jsonObject.get("lng").toString().equals("null")) {
                res[1] = "null";
            } else {
                res[1] = jsonObject.get("lng").toString();
            }

            resultList.add(res);
        }
        return resultList;
    }
}
