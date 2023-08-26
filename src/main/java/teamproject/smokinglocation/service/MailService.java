package teamproject.smokinglocation.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.dto.MailDto;

@Service
@Slf4j
@AllArgsConstructor
public class MailService {
    /*
     *  주의사항
		application.properties stmp 설정값 필수 
		메일 전송 계정이 "2차 인증" 을 해야되는 계정이면 오류 발생 
		본인의 경우 구글 "앱 비밀번호" 를 만들어 해결했지만 다른 경우도 해결해야 될 상황이 올 수 있음
	*/
	private JavaMailSender emailSender;
    public void sendSimpleMessage(MailDto mailDto) {
    	log.debug("MailService -  sendSimpleMessage Start");
    	SimpleMailMessage message = new SimpleMailMessage();
        
        // FROM 
        message.setFrom("smokadmin@smokinglocation.com"); //From 안먹음 ....  application.properties에 설정된 계정 FROM 잡힘 
        
        // TO 
        message.setTo(mailDto.getAddress()); 
        
        // 메일 제목 
        message.setSubject("[서울시흡연구역]"+mailDto.getName()+ "님 답변이 달렸습니다.");
        
        // 메일 내용 작성 
        StringBuilder sb = new StringBuilder();
        sb.append("이 메세지는 www.smokinglocation.com 에서 자동발송 된 메일입니다. ").append("\n");
        sb.append("이 메세지는 www.smokinglocation.com 에서 자동발송 된 메일입니다. ").append("\n");
        message.setText(sb.toString());
        
        // TEST DATA
        //message.setTo("kim9757di@naver.com");
        //message.setSubject(" 안녕하세요 한글TEST  ");
	    
	    
        emailSender.send(message);
        log.debug("MailService -  sendSimpleMessage End");
    }
}
