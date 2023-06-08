package teamproject.smokinglocation.dto.pathDataDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RouteData {
    @JsonProperty("traoptimal")
    private List<Traoptimal> traoptimal;
}
