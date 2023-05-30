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
public class FacilityGuro {
    @JsonProperty("개수")
    private String size;
    @JsonProperty("건물명")
    private String dataCreateDate;
    @JsonProperty("경도")
    private String installationDate;
    @JsonProperty("기준일자")
    private String installationLocation;
    @JsonProperty("도로명주소")
    private String installationEntity;
    @JsonProperty("시설구분")
    private String facilityForm;
    @JsonProperty("위도")
    private String indexNum;
    @JsonProperty("지번주소")
    private String manageFacility;
}
