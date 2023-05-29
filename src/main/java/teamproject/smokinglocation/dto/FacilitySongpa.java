package teamproject.smokinglocation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacilitySongpa {
    @JsonProperty("건물명")
    private String buildingName;
    @JsonProperty("구분")
    private String type;
    @JsonProperty("도로명주소")
    private String roadName;
}
