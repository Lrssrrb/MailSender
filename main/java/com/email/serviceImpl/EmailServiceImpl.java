// Java Program to Illustrate Creation Of
// Service implementation class

package com.email.serviceImpl;

// Importing required classes
import com.email.entity.EmailDetails;
import com.email.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired 
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}") private String sender;

	public String sendSimpleMail(EmailDetails details)
	{
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		try {
		helper=new MimeMessageHelper(message,true);
		helper.setTo(details.getReceiver());
		helper.setSubject(details.getSubject());
		helper.setText(details.getMsgBody());
		javaMailSender.send(message);

			return "Mail Sent Successfully...";
		}

		catch (Exception e) {
			return e.getMessage();//"Error while Sending Mail";
		}
	}

	public String
	sendMailWithAttachment(EmailDetails details)
	{
		MimeMessage mimeMessage
			= javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {

			mimeMessageHelper
				= new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getReceiver());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(
				details.getSubject());

			FileSystemResource file
				= new FileSystemResource(
					new File(details.getAttachment()));
			
			mimeMessageHelper.addAttachment(file.getFilename(), file);
			
			javaMailSender.send(mimeMessage);
			return "Mail sent Successfully";
		}

		catch (MessagingException e) {
			return "Error while sending mail!!!";
		}
	}
	
	
}
