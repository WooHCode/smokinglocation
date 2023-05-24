package teamproject.smokinglocation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityJongno {
    @JsonProperty("도로명")
    private String roadName;
    @JsonProperty("설치 장소 유형")
    private String facilityLocationType;
    @JsonProperty("설치장소")
    private String installationLocation;
    @JsonProperty("수거 쓰레기 종류")
    private String trashType;
    @JsonProperty("연번")
    private String indexNum;
    @JsonProperty("형태")
    private String type;
}
