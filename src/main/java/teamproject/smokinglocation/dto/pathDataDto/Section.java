package teamproject.smokinglocation.dto.pathDataDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Section {
    @JsonProperty("pointIndex")
    private int pointIndex;
    @JsonProperty("pointCount")
    private int pointCount;
    @JsonProperty("distance")
    private int distance;
    @JsonProperty("name")
    private String name;
    @JsonProperty("congestion")
    private int congestion;
    @JsonProperty("speed")
    private int speed;
}
