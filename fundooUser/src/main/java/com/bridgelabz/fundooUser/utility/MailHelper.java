package com.bridgelabz.fundooUser.utility;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHelper
{
	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	
	 //Method 2
	public static String getUrl(long id) 
	{
		return "http://localhost:8080/fundooNotes/user/"+ TokenUtil.createToken(id); 
	}
	public static String getUrl2(long id) 
	{
		return "http://localhost:4200/fundooNotes/user/"+ TokenUtil.createToken(id); 
	}
	
	
	
	public static String getToken(long id)
	{
		return TokenUtil.createToken(id);
	}
	
	public static void sendEmail(Session session, String toEmail, String subject, String body){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("no_reply@example.com", "FundooNotes"));

	      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
}
































/*
 * import java.util.Properties;
 * 
 * import javax.mail.Message; import javax.mail.MessagingException; import
 * javax.mail.PasswordAuthentication; import javax.mail.Session; import
 * javax.mail.Transport; import javax.mail.internet.InternetAddress; import
 * javax.mail.internet.MimeMessage;
 * 
 * public class MailHelper { //Method 1 public static void send(String from,
 * String password, String to, String sub, String msg) { // Get properties
 * object Properties props = new Properties(); props.put("mail.smtp.host",
 * "smtp.gmail.com"); props.put("mail.smtp.socketFactory.port", "465");
 * props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.port", "465");
 * 
 * // get Session Session session = Session.getDefaultInstance(props, new
 * javax.mail.Authenticator() { protected PasswordAuthentication
 * getPasswordAuthentication() { return new PasswordAuthentication(from,
 * password); } });
 * 
 * // compose message try { MimeMessage message = new MimeMessage(session);
 * message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
 * message.setSubject(sub); message.setText(msg); // send message
 * Transport.send(message); System.out.println("message sent successfully"); }
 * catch (MessagingException e) { throw new RuntimeException(e); }
 * 
 * }
 * 
 * //Method 2 public static String getUrl(long id) { return
 * "http://localhost:4200/user/"+ TokenUtil.createToken(id)+"/valid"; }
 * 
 * 
 * }
 */