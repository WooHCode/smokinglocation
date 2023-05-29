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
public class FacilityDongjak {
    @JsonProperty("경도")
    private String longitude;
    @JsonProperty("관리부서")
    private String manageFacility;
    @JsonProperty("구   분")
    private String type;
    @JsonProperty("위도")
    private String latitude;
    @JsonProperty("장    소")
    private String facilityName;
    @JsonProperty("주소")
    private String installationLocation;
}
