package teamproject.smokinglocation.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter @Setter
@AllArgsConstructor
public class TokenInfo {
    private HttpStatus status;
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String roles;
    private String name;
}
