package com.email.entity;

import lombok.Data;

@Data
public class EmailDetails {

	private String receiver;
    private String msgBody;
    private String subject;
    private String attachment;
    
}
