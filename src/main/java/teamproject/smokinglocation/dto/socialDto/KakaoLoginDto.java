package teamproject.smokinglocation.dto.socialDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KakaoLoginDto {
	private String email;
	private String name;
	private String birth;
}
