package teamproject.smokinglocation.dto.inquiryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {
    private Long id;
    private String title;
    private String content;
    private String reply;
    private Long memberId;
}
