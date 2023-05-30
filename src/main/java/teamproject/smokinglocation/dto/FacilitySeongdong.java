package teamproject.smokinglocation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacilitySeongdong {
    @JsonProperty("구분")
    private String type; // 구분
    @JsonProperty("규모")
    private String size;
    @JsonProperty("설치 주체")
    private String installationEntity; // 설치 주체
    @JsonProperty("설치위치")
    private String installationLocation;
    @JsonProperty("연번")
    private String indexNum;
}
