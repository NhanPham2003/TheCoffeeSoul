package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
 
import org.springframework.stereotype.Service;
 
@Service
public class SendMailServices {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendSimpleEmail(String toEmail,
            String subject,
            String body
		) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("nhanphph23607@fpt.edu.vn");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		mailSender.send(message);
		System.out.println("Mail Send...");
		
		
		}
	
	public void prepareAndSend(String recipient, String message) {
	    MimeMessagePreparator messagePreparator = mimeMessage -> {
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	        messageHelper.setFrom("sample@dolszewski.com");
	        messageHelper.setTo(recipient);
	        messageHelper.setSubject("Sample mail subject");
	        messageHelper.setText(message);
	    };
	    try {
	        mailSender.send(messagePreparator);
	    } catch (MailException e) {
	        // runtime exception; compiler will not force you to handle it
	    }
	}
	
//	 public void sendEmailWithAttachment(String to, String from, String subject, String content, String pathToAttachment) throws MessagingException {
//	        MimeMessage message = javaMailSender.createMimeMessage();
//	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//	        helper.setTo(to);
//	        helper.setFrom(from);
//	        helper.setSubject(subject);
//	        helper.setText(content);
//
//	        Resource file = resourceLoader.getResource(pathToAttachment);
//	        helper.addAttachment(file.getFilename(), file);
//
//	        javaMailSender.send(message);
//	    }
	
//	public void sendEmail(Mail mail) throws MessagingException, IOException {
//        MimeMessage message = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message,
//                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                StandardCharsets.UTF_8.name());
//        helper.addAttachment("template-cover.png", new ClassPathResource("javabydeveloper-email.PNG"));
//        Context context = new Context();
//        context.setVariables(mail.getProps());
//    
//        String html = templateEngine.process("newsletter-template", context);
//        helper.setTo(mail.getMailTo());
//        helper.setText(html, true);
//        helper.setSubject(mail.getSubject());
//        helper.setFrom(mail.getFrom());
//        emailSender.send(message);
//    }

//	public void sendHtmlEmail(String toEmail, String subject, String htmlContent) throws MessagingException {
//		MimeMessage message = javaMailSender.createMimeMessage() ;
//		MimeMessageHelper helper = new MimeMessageHelper(message, true);
//		helper.setTo(toEmail);
//		helper.setSubject(subject);
//		helper.setText(htmlContent, true);
//		javaMailSender.send(message);
//	}
}
