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
public class FacilityGangbuk {
    @JsonProperty("규모")
    private String size;
    @JsonProperty("데이터기준일자")
    private String dataCreateDate;
    @JsonProperty("설치 위치")
    private String installationLocationDetail;
    @JsonProperty("설치 주체")
    private String installationEntity;
    @JsonProperty("시설 구분")
    private String type;
    @JsonProperty("시설형태")
    private String facilityForm;
    @JsonProperty("자치구명")
    private String districtName;
    @JsonProperty("주소")
    private String installationLocation;
}
