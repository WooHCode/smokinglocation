package teamproject.smokinglocation.controller;

import lombok.Getter;

@Getter
public enum EmailData {
    header("[서울시흡연구역] "),
	answer("님이 작성하신 문의가 성공적으로 접수되었습니다."),
	template1("/email/templateMail1");
    private final String emailString;

    EmailData(String emailString) {
        this.emailString = emailString;
    }
}
