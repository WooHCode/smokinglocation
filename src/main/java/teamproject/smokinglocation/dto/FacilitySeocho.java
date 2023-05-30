package teamproject.smokinglocation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacilitySeocho {
    @JsonProperty("규모")
    private String size;
    @JsonProperty("설치 위치")
    private String installationLocation;
    @JsonProperty("설치일자")
    private String installationDate;
    @JsonProperty("설치주체")
    private String installationEntity;
    @JsonProperty("시설형태")
    private String facilityForm;
    @JsonProperty("연번")
    private String indexNum;
    @JsonProperty("자치구명")
    private String districtName;
    @JsonProperty("청소관리")
    private String manageOk;
    @JsonProperty("추가시설")
    private String extraFacility;
    @JsonProperty("해당동")
    private String dong;

}
