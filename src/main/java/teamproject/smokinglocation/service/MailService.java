package teamproject.smokinglocation.service;

import java.util.HashMap;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.controller.EmailData;
import teamproject.smokinglocation.userEnitiy.Member;

@Service
@Slf4j
@AllArgsConstructor
public class MailService {
	
    private final MemberService memberService;
    
    private final JavaMailSender emailSender;
    
    private final SpringTemplateEngine templateEngine;
    
    /*
     *  주의사항
		application.properties stmp 설정값 필수 
		메일 전송 계정이 "2차 인증" 을 해야되는 계정이면 오류 발생 
		본인의 경우 구글 "앱 비밀번호" 를 만들어 해결했지만 다른 경우도 해결해야 될 상황이 올 수 있음
	*/
    public void sendSimpleMessage(Long id, String content) throws Exception{
    	log.info("MailService -  sendSimpleMessage Start");
    	Member member = memberService.findById(id);

    	//기존
    	//SimpleMailMessage message = new SimpleMailMessage();
    	
    	//html template 
    	MimeMessage message = emailSender.createMimeMessage();

        
        //기존 
        //message.setTo(member.getMemberId()); 
    	
    	//html template
    	message.addRecipients( MimeMessage.RecipientType.TO  , member.getMemberId());
    	
        // 메일 제목 
        message.setSubject(EmailData.header.getEmailString()+member.getMemberName()+EmailData.answer.getEmailString());
        
        // 메일 내용 작성 
        //템플릿에 전달할 데이터 설정
        HashMap<String, String> emailValues = new HashMap<>();
    	emailValues.put("name", member.getMemberName());
        emailValues.put("mainText", content);
    	message.setText(setContext(emailValues , EmailData.template1.getEmailString()) , "utf-8" , "html");
    	
    	// FROM 
    	//From 안먹음 ....  application.properties에 설정된 계정 FROM 잡힘
        //message.setFrom("smokadmin@smokinglocation.com");  
	    
        emailSender.send(message);
        log.info("MailService -  sendSimpleMessage End");
    }

    public void sendReplyMessage(Long id, String content) throws Exception{
        log.info("MailService -  sendSimpleMessage Start");
        Member member = memberService.findById(id);

        //기존
        //SimpleMailMessage message = new SimpleMailMessage();

        //html template
        MimeMessage message = emailSender.createMimeMessage();


        //기존
        //message.setTo(member.getMemberId());

        //html template
        message.addRecipients( MimeMessage.RecipientType.TO  , member.getMemberId());

        // 메일 제목
        message.setSubject(EmailData.header.getEmailString()+member.getMemberName()+EmailData.rePlyAnswer.getEmailString());

        // 메일 내용 작성
        //템플릿에 전달할 데이터 설정
        HashMap<String, String> emailValues = new HashMap<>();
        emailValues.put("name", member.getMemberName());
        emailValues.put("mainText", content);
        message.setText(setContext(emailValues , EmailData.template2.getEmailString()) , "utf-8" , "html");

        // FROM
        //From 안먹음 ....  application.properties에 설정된 계정 FROM 잡힘
        //message.setFrom("smokadmin@smokinglocation.com");

        emailSender.send(message);
        log.info("MailService -  sendSimpleMessage End");
    }
    
    private String setContext(HashMap<String, String> emailValues , String emailTemplate) {
    	Context context =  new Context();
        emailValues.forEach((key, value)->{
            context.setVariable(key, value);
        });
    	return templateEngine.process(emailTemplate, context);
    }
}
