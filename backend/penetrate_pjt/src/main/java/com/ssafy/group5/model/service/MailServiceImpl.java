package com.ssafy.group5.model.service;
import java.util.Random;
 
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
 
@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSender mailSender;
 
	@Override
	public String makeCode(int size) {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
			do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}
			} while (sb.length() < size);
		return sb.toString();
	}
 
	@Override
	public String makeHtml(String type, String code) {
		String html = null;
		switch(type) {
		case "register":
			html = "<br>\n"
		            + "<h2>#$@!@~!에서 발송한 이메일 인증 코드 안내 메일입니다.</h2>\n"
		            + "<br/>\n"
		            + "<p>아래 코드를 입력해 이메일 인증을 완료해주시기 바랍니다.</p>\n"
		            + "<p><strong>" + code + "</strong></p>";
			break;
		case "findpw":
			html = "<br>\n"
		            + "<h2>#$@!@~!에서 발송한 임시 비밀번호 발급 안내 메일입니다.</h2>\n"
		            + "<br/>\n"
		            + "<p>아래 임시 비밀번호로 로그인 후 비밀번호를 변경해주시기 바랍니다.</p>\n"
		            + "<p><strong>" + code + "</strong></p>";
			break;
		}
		return html;
	}
 
	@Override
	public String sendMail(String type, String email) {
		//타입에 따라
		//1. 인증코드 만들기
		//2. html string만들기		
		String code = null, html = null, subject = null;
		switch(type) {
		case "register":
			code = makeCode(6);
			html = makeHtml(type, code);
			subject = "[여행어쩌구] 이메일 인증 코드";
			break;
		case "findpw":
			code = makeCode(10);
			html = makeHtml(type, code);
			subject = "[여행어쩌구] 임시 비밀번호 발급";
			break;
		}
		
		//공통 - 메일보내기
		MimeMessage mail = mailSender.createMimeMessage();
		try {
			mail.setSubject(subject,"utf-8");
			mail.setText(html,"utf-8","html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "error";
		}
		
		return code;
	}
 
}