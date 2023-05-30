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
public class FacilityJunggu {
    @JsonProperty("구분")
    private String type;

    @JsonProperty("규모")
    private String size;

    @JsonProperty("데이터기준일자")
    private String dataCreateDate;

    @JsonProperty("설치도로명주소")
    private String roadName;

    @JsonProperty("설치연월")
    private String installationDate;

    @JsonProperty("설치위치")
    private String installationLocation;

    @JsonProperty("설치주체")
    private String installationEntity;

    @JsonProperty("운영관리")
    private String manageFacility;
}
