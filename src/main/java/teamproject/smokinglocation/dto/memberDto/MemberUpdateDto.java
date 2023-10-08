package teamproject.smokinglocation.dto.memberDto;

import lombok.Data;

@Data
public class MemberUpdateDto {
    private String memberId;
    private String memberName;
    private String password;
    private String provider;
}
