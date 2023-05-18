package teamproject.smokinglocation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Facility {
    @JsonProperty("자치구명")
    private String districtName; // 자치구명
    @JsonProperty("시설 구분")
    private String facilityType; // 시설 구분
    @JsonProperty("시설형태")
    private String facilityForm; // 시설형태
    @JsonProperty("서울특별시 용산구 설치 위치")
    private String locationInYongsan; // 서울특별시 용산구 설치 위치
    @JsonProperty("설치 주체")
    private String installationEntity; // 설치 주체
    @JsonProperty("위도")
    private String latitude; // 위도
    @JsonProperty("경도")
    private String longitude; // 경도
}
