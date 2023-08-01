package teamproject.smokinglocation.dto.memberDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveSpotDto {

    private String findLng;
    private String findLat;
    private String findLoc;
    private String refreshToken;
}
