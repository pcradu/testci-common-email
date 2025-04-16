package org.apache.commons.mail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import javax.mail.Session;


import static org.junit.Assert.*;

public class EmailTest {
	

    private static final String[] TEST_EMAILS = {
        "ccc@abc.com", "aaa@abc.com", "bbb@abc.com"
    };


    private EmailConcrete email;


    @Before
    public void setUpEmailTest() throws Exception {
        email = new EmailConcrete();
    }


    @After
    public void tearDownEmailTest() throws Exception {
        email = null;
    }

    @Test
    public void testAddBcc() throws EmailException {
        email.addBcc(TEST_EMAILS);
        assertEquals(3, email.getBccAddresses().size());
    }
    
    @Test
    public void testAddCc() throws Exception {
        email.addCc("cc@abc.com");
        assertEquals(1, email.getCcAddresses().size());
    }
    
    @Test
    public void testAddHeader() throws Exception {
        email.addHeader("header", "value");
        assertEquals("value", email.headers.get("header"));
    }
    @Test
    public void testAddReplyTo() throws Exception {
        email.addReplyTo("reply@abc.com", "John Smith");
        assertEquals(1, email.getReplyToAddresses().size());
    }
    @Test
    public void testBuildMimeMessage() throws Exception {
        email.buildMimeMessage();
        assertNotNull(email.getMimeMessage());
    }
    @Test
    public void testGetHostName() {
        EmailConcrete email = new EmailConcrete();
        email.hostName = "smtp.abc.com"; 

        Session session = email.getMailSession();
       assertNotNull("Session should not be null", session);
        assertEquals("smtp.abc.com", email.hostName);
    }

    
    @Test
    public void testGetMailSession() throws Exception {
        Session session = email.getMailSession();
        assertNotNull(session);
    }

    @Test
    public void testGetSentDate() {
        Date currentdate = new Date();
        email.setSentDate(currentdate);
        assertEquals(currentdate, email.getSentDate());
    }
    
    @Test
    public void testGetSocketConnectionTimeout() {
        final int TIMEOUT = 3000;  
        email.setSocketConnectionTimeout(TIMEOUT);
        assertEquals(TIMEOUT, email.getSocketConnectionTimeout());
    }
    @Test
    public void testSetFrom() throws Exception {
        Email returned = email.setFrom("test@abc.com");
        assertEquals("test@abc.com", email.getFromAddress().getAddress());
        assertEquals(email, returned); 
    }

}
