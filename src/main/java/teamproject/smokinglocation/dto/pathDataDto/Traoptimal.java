package teamproject.smokinglocation.dto.pathDataDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Traoptimal {
    @JsonProperty("summary")
    private Summary summary;
    @JsonProperty("path")
    private List<List<Float>> path;
    @JsonProperty("section")
    private List<Section> section;
    @JsonProperty("guide")
    private List<Guide> guide;
}
