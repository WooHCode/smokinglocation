package teamproject.smokinglocation.dto.pathDataDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Goal {
    @JsonProperty("location")
    private List<Float> location;
    @JsonProperty("dir")
    private int dir;
}
