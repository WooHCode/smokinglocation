package teamproject.smokinglocation.dto.pathDataDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Summary {
    @JsonProperty("start")
    private Start start;
    @JsonProperty("goal")
    private Goal goal;
    @JsonProperty("distance")
    private int distance;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("etaServiceType")
    private int etaServiceType;
    @JsonProperty("departureTime")
    private String departureTime;
    @JsonProperty("bbox")
    private List<List<Float>> bbox;
    @JsonProperty("tollFare")
    private int tollFare;
    @JsonProperty("taxiFare")
    private int taxiFare;
    @JsonProperty("fuelPrice")
    private int fuelPrice;
}
