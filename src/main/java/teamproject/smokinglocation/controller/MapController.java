package teamproject.smokinglocation.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.dto.NearbyLocationDto;
import teamproject.smokinglocation.entity.TotalData;
import teamproject.smokinglocation.service.DataResponseService;
import teamproject.smokinglocation.service.LocationNearbyService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MapController {

    @Value("${naver.map.client.id}")
    private String naverMapClientId;
    
    private final DataResponseService responseService;
    private final LocationNearbyService nearbyService;
    private static String myLatitude = "";
    private static String myLongitude = "";
    
    @GetMapping("/")
    public String home() {
        return "redirect:/map";
    }

    @GetMapping("/map")
    public String showMap(Model model) {
        List<TotalData> totalData = responseService.getTotalData();
        if (!myLatitude.equals("") && !myLongitude.equals("")) {
            model.addAttribute("myLatitude", myLatitude);
            model.addAttribute("myLongitude",myLongitude);
        }

        String accessToken = (String) model.getAttribute("accessToken") == null ? "" : (String) model.getAttribute("accessToken");
        String refreshToken = (String) model.getAttribute("refreshToken") == null ? "" : (String) model.getAttribute("refreshToken"); 

        model.addAttribute("accessToken", accessToken);
        model.addAttribute("refreshToken", refreshToken);
        model.addAttribute("facilities", totalData);
        model.addAttribute("naverMapClientId", naverMapClientId);

        log.info("===========totalDataLoading===========");
        log.info("totalData = {}", totalData);
        log.info("accessToken = {}", accessToken);
        log.info("refreshToken = {}", refreshToken);
        log.info("===========totalDataLoadingFinish============");
        return "map";
    }

    @GetMapping("/map/select")
    public String selectGu(Model model,String gu) {
        return setModelFacilitiesAndNaverMap(model,gu);
    }

    /**
     * 현재 위치 주변 500m이내의 좌표들을 추출해서 반환하는 API
     * @param latitude
     * @param longitude
     * @return
     */
    @GetMapping("/map/nearby")
    @ResponseBody
    public List<NearbyLocationDto> getNearbyLocation(String latitude, String longitude) {
        myLatitude = latitude;
        myLongitude = longitude;
        log.info("myLatitude = {}", myLatitude);
        log.info("myLongitude = {}", myLongitude);
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
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getGangnamData());
            return "gangnam";
        } else if (gu.equals("강북")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getGangbukData());
            return "gangbuk";
        } else if (gu.equals("강서")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getGangseoData());
            return "gangseo";
        } else if (gu.equals("관악")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getGwanakData());
            return "gwanak";
        } else if (gu.equals("광진")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getGwangjinData());
            return "gwangjin";
        } else if (gu.equals("구로")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getGuroData());
            return "guro";
        } else if (gu.equals("노원")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getNowonData());
            return "nowon";
        } else if (gu.equals("동대문")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getDongdaemunData());
            return "dongdaemun";
        } else if (gu.equals("동작")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getDongjakData());
            return "dongjak";
        } else if (gu.equals("서대문")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getSeodaemunData());
            return "seodaemun";
        } else if (gu.equals("서초")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getSeochoData());
            return "seocho";
        } else if (gu.equals("성동")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getSeongdongData());
            return "seongdong";
        } else if (gu.equals("성북")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getSeongbukData());
            return "seongbuk";
        } else if (gu.equals("송파")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getSongpaData());
            return "songpa";
        } else if (gu.equals("양천")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getYangcheonData());
            return "yangcheon";
        } else if (gu.equals("영등포")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getYeongdeungpoData());
            return "yeongdeungpo";
        } else if (gu.equals("용산")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getYongsanData());
            return "yongsan";
        } else if (gu.equals("종로")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getJongnoData());
            return "jongno";
        } else if (gu.equals("중구")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getJungguData());
            return "junggu";
        } else if (gu.equals("중랑")) {
            if (!myLatitude.equals("") && !myLongitude.equals("") && myLatitude != null && myLongitude != null) {
                model.addAttribute("myLatitude", myLatitude);
                model.addAttribute("myLongitude",myLongitude);
            }
            model.addAttribute("facilities", responseService.getJungnangData());
            return "jungnang";
        }
        return null;
    }
}
