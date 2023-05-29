package teamproject.smokinglocation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityNowon {
    @JsonProperty("경도")
    private String longitude;
    @JsonProperty("구분")
    private String type;
    @JsonProperty("규모")
    private String size;
    @JsonProperty("기준일자")
    private String dataCreateDate;
    @JsonProperty("설치일")
    private String installationDate;
    @JsonProperty("설치주체")
    private String installationEntity;
    @JsonProperty("연번")
    private String indexNum;
    @JsonProperty("운영관리")
    private String manageFacility;
    @JsonProperty("위도")
    private String latitude;
    @JsonProperty("위치")
    private String installationLocation;
}
