/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

    final String senderEmailID = "meghakhandelwal14@gmail.com";
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailServerPort = "465";
    String receiverEmailID = null;
    String username;
    String emailBody;
    static String emailSubject = "Welcome to Voyager shopping-cart";

    public MailService(String receiverEmailID,String username) {
        this.receiverEmailID = receiverEmailID;
        this.username = username;
        emailBody = "Dear " + username + "," +
                        "\n\nWelcome to Voyager Shopping-Cart" +
                        "\n\n We believe that spending time in the great outdoors is good for the soul. But with so many gear choices and possible activities, it can be difficult to get started. Thankfully our mission at Voyagers is to make getting outside easier and more fun. So, gear up and venture out! \n\n Happy Shopping" + username  + 
                        "\n\n\n\nBest Regards," +
                        "\n Team Voyagers";
        Properties props = new Properties();
        props.put("mail.smtp.user", senderEmailID);
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", emailServerPort);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        SecurityManager security = System.getSecurityManager();
        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(emailBody);
            msg.setSubject(emailSubject);
            msg.setFrom(new InternetAddress(senderEmailID));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiverEmailID));
            Transport.send(msg);
            System.out.println("Message send Successfully:)");
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }

    

   

}

