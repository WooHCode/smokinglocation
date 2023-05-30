package teamproject.smokinglocation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityGangnam {
    @JsonProperty("동명")
    private String roadName;
    @JsonProperty("설치주소")
    private String installationLocation;
    @JsonProperty("위치")
    private String location;
}
