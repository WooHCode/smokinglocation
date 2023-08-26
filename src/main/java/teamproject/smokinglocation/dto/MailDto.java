package teamproject.smokinglocation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class MailDto {
	 private String address;
	 private String name; 
	 private String title;
	 private String content;
}
