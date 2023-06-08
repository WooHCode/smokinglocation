package teamproject.smokinglocation.dto.pathDataDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Guide {
    @JsonProperty("pointIndex")
    private int pointIndex;
    @JsonProperty("type")
    private int type;
    @JsonProperty("instructions")
    private String instructions;
    @JsonProperty("distance")
    private int distance;
    @JsonProperty("duration")
    private int duration;
}
