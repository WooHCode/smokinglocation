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
public class FacilityYangcheon {
    @JsonProperty("규모(제곱미터)")
    private String size;

    @JsonProperty("데이터기준일자")
    private String dataCreateDate;

    @JsonProperty("설치 위치")
    private String installationLocation;

    @JsonProperty("설치주체")
    private String installationEntity;

    @JsonProperty("시설형태")
    private String facilityForm;
    @JsonProperty("연번")
    private String indexNum;
    @JsonProperty("자치구명")
    private String districtName;
}
