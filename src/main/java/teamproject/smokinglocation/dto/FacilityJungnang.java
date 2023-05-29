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
public class FacilityJungnang {
    @JsonProperty("시설명(업소)")
    private String buildingName;
    @JsonProperty("업종")
    private String business;
    @JsonProperty("주소")
    private String installationLocation;
    @JsonProperty("흡연실 개소수")
    private String installCount;
}
