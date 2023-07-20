package teamproject.smokinglocation.dto.memberDto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MemberRegisterRequestDto {
    private String name;
    private String email;
    private String password;
}
