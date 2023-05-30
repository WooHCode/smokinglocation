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
public class FacilityGwanak {
    @JsonProperty("구분")
    private String type;
    @JsonProperty("규모(제곱미터)")
    private String size;
    @JsonProperty("설치위치")
    private String installationLocation;
    @JsonProperty("설치주체")
    private String installationEntity;
    @JsonProperty("운영관리")
    private String manageFacility;
    @JsonProperty("자치구")
    private String districtName;
}
