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
			html = "<div style=\"margin:100px;\">\n"
					+ "    <h1>여기어떤데에서 발송한 이메일 인증 코드 안내 메일입니다.</h1>\n"
					+ "    <br>\n"
					+ "    <p>아래 코드를 회원가입 창으로 돌아가 입력해주세요.</p>\n"
					+ "    <br>\n"
					+ "    <div align=\"center\" style=\"border:1px solid black; font-family:verdana;\">\n"
					+ "        <h3 style=\"color:blue\"> 회원가입 인증 코드 </h3>\n"
					+ "        <div style=\"font-size:130%\">"+ code + "</div>\n"
					+ "    </div>\n"
					+ "    <br/>\n"
					+ "</div>";
			break;
		case "findpw":
			html = "<div style=\"margin:100px;\">\n"
					+ "    <h1>여기어떤데에서 발송한 임시 비밀번호 안내 메일입니다.</h1>\n"
					+ "    <br>\n"
					+ "    <p>아래 발급된 임시 비밀번호로 로그인해주세요.</br>임시 비밀번호는 보안에 취약하므로 로그인 후 비밀번호를 변경하시기 바랍니다.</p>\n"
					+ "    <br>\n"
					+ "    <div align=\"center\" style=\"border:1px solid black; font-family:verdana;\">\n"
					+ "        <h3 style=\"color:blue\"> 임시 비밀번호 </h3>\n"
					+ "        <div style=\"font-size:130%\">"+ code + "</div>\n"
					+ "    </div>\n"
					+ "    <br/>\n"
					+ "</div>";
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
			subject = "[여기어떤데] 이메일 인증 코드";
			break;
		case "findpw":
			code = makeCode(10);
			html = makeHtml(type, code);
			subject = "[여기어떤데] 임시 비밀번호 발급 ";
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