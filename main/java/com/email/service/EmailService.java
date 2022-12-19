package com.email.service;

import com.email.entity.EmailDetails;

public interface EmailService {

	String sendSimpleMail(EmailDetails details);
	 
    String sendMailWithAttachment(EmailDetails details);
}
