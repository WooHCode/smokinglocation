package teamproject.smokinglocation.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamproject.smokinglocation.dto.*;
import teamproject.smokinglocation.entity.Gangnam;
import teamproject.smokinglocation.entity.TotalData;
import teamproject.smokinglocation.service.DataResponseService;
import teamproject.smokinglocation.service.DataService;
import teamproject.smokinglocation.service.LatlngService;
import teamproject.smokinglocation.service.LocationNearbyService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MapController {

    @Value("${naver.map.client.id}")
    private String naverMapClientId;

    private final DataResponseService responseService;
    private final LocationNearbyService nearbyService;


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

    @GetMapping("/map/select")
    public String selectGu(Model model,String gu) {
        return setModelFacilitiesAndNaverMap(model,gu);
    }

    @GetMapping("/map/nearby")
    @ResponseBody
    public List<NearbyLocationDto> getNearbyLocation(String latitude, String longitude) {
        List<TotalData> totalData = responseService.getTotalData();
        List<NearbyLocationDto> nearbyDtos = totalData.stream()
                .map(data -> {
                    NearbyLocationDto nearbyDto = NearbyLocationDto.builder()
                            .latitude(data.getLat())
                            .longitude(data.getLon())
                            .location(data.getLocation())
                            .build();
                    return nearbyDto;
                })
                .collect(Collectors.toList());
        List<NearbyLocationDto> result = nearbyService.extractNearbyLocations(latitude, longitude, nearbyDtos);
        return result;
    }

    private String setModelFacilitiesAndNaverMap(Model model, String gu) {
        model.addAttribute("naverMapClientId", naverMapClientId);

        if (gu.equals("강남")) {
            model.addAttribute("facilities", responseService.getGangnamData());
            return "gangnam";
        } else if (gu.equals("강북")) {
            model.addAttribute("facilities", responseService.getGangbukData());
            return "gangbuk";
        } else if (gu.equals("강서")) {
            model.addAttribute("facilities", responseService.getGangseoData());
            return "gangseo";
        } else if (gu.equals("관악")) {
            model.addAttribute("facilities", responseService.getGwanakData());
            return "gwanak";
        } else if (gu.equals("광진")) {
            model.addAttribute("facilities", responseService.getGwangjinData());
            return "gwangjin";
        } else if (gu.equals("구로")) {
            model.addAttribute("facilities", responseService.getGuroData());
            return "guro";
        } else if (gu.equals("노원")) {
            model.addAttribute("facilities", responseService.getNowonData());
            return "nowon";
        } else if (gu.equals("동대문")) {
            model.addAttribute("facilities", responseService.getDongdaemunData());
            return "dongdaemun";
        } else if (gu.equals("동작")) {
            model.addAttribute("facilities", responseService.getDongjakData());
            return "dongjak";
        } else if (gu.equals("서대문")) {
            model.addAttribute("facilities", responseService.getSeodaemunData());
            return "seodaemun";
        } else if (gu.equals("서초")) {
            model.addAttribute("facilities", responseService.getSeochoData());
            return "seocho";
        } else if (gu.equals("성동")) {
            model.addAttribute("facilities", responseService.getSeongdongData());
            return "seongdong";
        } else if (gu.equals("성북")) {
            model.addAttribute("facilities", responseService.getSeongbukData());
            return "seongbuk";
        } else if (gu.equals("송파")) {
            model.addAttribute("facilities", responseService.getSongpaData());
            return "songpa";
        } else if (gu.equals("양천")) {
            model.addAttribute("facilities", responseService.getYangcheonData());
            return "yangcheon";
        } else if (gu.equals("영등포")) {
            model.addAttribute("facilities", responseService.getYeongdeungpoData());
            return "yeongdeungpo";
        } else if (gu.equals("용산")) {
            model.addAttribute("facilities", responseService.getYongsanData());
            return "yongsan";
        } else if (gu.equals("종로")) {
            model.addAttribute("facilities", responseService.getJongnoData());
            return "jongno";
        } else if (gu.equals("중구")) {
            model.addAttribute("facilities", responseService.getJungguData());
            return "junggu";
        } else if (gu.equals("중랑")) {
            model.addAttribute("facilities", responseService.getJungnangData());
            return "jungnang";
        }
        return null;
    }
}

