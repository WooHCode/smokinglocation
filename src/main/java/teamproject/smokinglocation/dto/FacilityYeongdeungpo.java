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
public class FacilityYeongdeungpo {
    @JsonProperty("경도")
    private String longitude;

    @JsonProperty("관리")
    private String manageFacility;

    @JsonProperty("관리여부")
    private String manageOk;

    @JsonProperty("규모(제곱미터)")
    private String size;

    @JsonProperty("설치 위치")
    private String installationLocation;

    @JsonProperty("설치 주체")
    private String installationEntity;

    @JsonProperty("설치일")
    private String installationDate;

    @JsonProperty("시설 구분")
    private String facilityType;

    @JsonProperty("시설형태")
    private String facilityForm;

    @JsonProperty("위도")
    private String latitude;

    @JsonProperty("자치구")
    private String districtName;
}
