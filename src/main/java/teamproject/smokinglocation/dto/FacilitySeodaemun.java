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
public class FacilitySeodaemun {
    @JsonProperty("관리기관")
    private String manageFacility;
    @JsonProperty("관리여부")
    private String manageOk;
    @JsonProperty("규모(㎡)")
    private String size;
    @JsonProperty("설치위치")
    private String installationLocation;
    @JsonProperty("설치위치 상세")
    private String installationLocationDetail;
    @JsonProperty("설치일")
    private String installationDate;
    @JsonProperty("설치주체")
    private String installationEntity;
    @JsonProperty("시설구분")
    private String type;
    @JsonProperty("시설형태")
    private String facilityForm;
    @JsonProperty("연번")
    private String indexNum;
    @JsonProperty("자치구")
    private String districtName;


}
