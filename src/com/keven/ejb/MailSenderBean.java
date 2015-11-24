package com.keven.ejb;

import java.util.Date;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.xml.ws.api.transport.tcp.SelectOptimalTransportFeature.Transport;
import com.sun.xml.ws.runtime.dev.Session;

/**
 * Session Bean implementation class MailSenderBean
 */
@Stateless
@LocalBean
public class MailSenderBean {

	/**
	 * Default constructor.
	 */
	public MailSenderBean() {
		// TODO Auto-generated constructor stub
	}

	public void sendEmail(String fromEmail, String username, String password, String toEmail, String subject,
			String message) {

		try {
			//Set mail service properties
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");	
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFacrory.port", "465");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			//Set mail service stateless session
			javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, null);
			//Set alive mail session debug
			mailSession.setDebug(true);
			//Mail service message
			Message mailMessage = new MimeMessage(mailSession);

			mailMessage.setFrom(new InternetAddress(fromEmail));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mailMessage.setContent(message,"text/html");
			mailMessage.setSubject(subject);
			mailMessage.setSentDate(new Date());
			
			javax.mail.Transport transport = mailSession.getTransport("smtps");
			transport.connect("smtp.gmail.com", username, password);
			
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
//			transport.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
