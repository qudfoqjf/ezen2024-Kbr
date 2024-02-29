package ezenweb.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service//빈 등록
public class EmailService {

    // SMTP  :  간이 우편 전송 프로토콜 (메일 전송)
        // - 자바에서 메일 보내기 (네이버 계정)
        // 1. SMTP 라이브러리 필요: https: //start.spring.io/
        // 2. Dependencies : Java Mail Sender
            //  implementation 'org.springframework.boot:spring-boot-starter-mail'
        // 3. 이메일 전송
            //1. 이메일 내용을 구성 => 구성 포멧 : MIME(SMTP 를 사용할 때 사용되는 포멧)

    // 1. java(spring) 지원하는 smtp 객체 필요 = javaMailSender
    @Autowired
    private JavaMailSender javaMailSender;  // javamail 을 제공하는 객체 다른 함수에서도 사용하기 위함
    public void send(String toEmail,String subject,String content){
        try {
        // * 메일 내용들을 포멧하기 위한 MIME 형식 객체
        MimeMessage message = javaMailSender.createMimeMessage();//
        //1. 메일 구성  //
            // MimeMessageHelper(mime객체, 첨부파일여부, 인코딩타입); 내용물 구성
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true,"utf-8");
        //2. 메일 보내는 사람
        mimeMessageHelper.setFrom("c8571@naver.com");   //관리자 이메일
        //3. 메일 받는 사람
        mimeMessageHelper.setTo(toEmail);     //클라이언트(회원) 이메일(매개변수)
        //4. 메일 제목
        mimeMessageHelper.setSubject(subject);   // (매개변수)
        //5. 메일 내용
        mimeMessageHelper.setText(content);    //(매개변수)
        // 메일 전송
        javaMailSender.send(message);
        }catch (Exception e){
            System.out.println("이메일 전송 실패" + e);
        }
    }
}
