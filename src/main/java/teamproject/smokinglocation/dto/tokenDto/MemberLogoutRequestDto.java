package teamproject.smokinglocation.dto.tokenDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberLogoutRequestDto {
    private String accessToken;
    private String OAuthToken;
    private String provider;
}
