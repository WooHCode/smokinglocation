package teamproject.smokinglocation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class NearbyLocationDto {
    private String latitude;
    private String longitude;
    private String location;
}
