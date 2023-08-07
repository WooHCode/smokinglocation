package teamproject.smokinglocation.dto.socialDto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SocialLoginDto {
	private String clientId;
	private String secretId;
	private String redirectUrl;
}
