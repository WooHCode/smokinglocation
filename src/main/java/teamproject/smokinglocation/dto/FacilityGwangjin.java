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
public class FacilityGwangjin {
    @JsonProperty("구분")
    private String type; // 구분

    @JsonProperty("시설명")
    private String facilityName;

    @JsonProperty("실외     실내")
    private String outdoor;

    @JsonProperty("연번")
    private String indexNum;

    @JsonProperty("영업소소재지(도로 명)")
    private String roadName;

    @JsonProperty("흡연실 형태")
    private String smokingLocationType;
}
