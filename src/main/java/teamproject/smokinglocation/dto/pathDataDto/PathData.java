package teamproject.smokinglocation.dto.pathDataDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PathData {
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("currentDateTime")
    private String currentDateTime;
    @JsonProperty("route")
    private RouteData route;
}
