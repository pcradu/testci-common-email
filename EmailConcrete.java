package org.apache.commons.mail;

import javax.mail.Session;
import java.util.Properties;

public class EmailConcrete extends Email {
	
	

    
    @Override
    public Session getMailSession() {
     
        if (hostName == null || hostName.isEmpty()) {
            hostName = "smtp.abc.com";  
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", hostName);
        props.put("mail.smtp.port", smtpPort);

        return Session.getInstance(props);
    }

    @Override
    public Email setMsg(String msg) throws EmailException {
        this.subject = msg; 
        return this;
    }
   

}

