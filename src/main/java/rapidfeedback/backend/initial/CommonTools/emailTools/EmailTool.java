package rapidfeedback.backend.initial.CommonTools.emailTools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * send email to others
 * @author cyt
 * @date 2020/5/15
 */

@Slf4j
@Service
public class EmailTool {

//    Properties emailProperties;
//    Session mailSession;
//    MimeMessage emailMessage;
//
//    @Autowired
//    private JavaMailSenderImpl mailSender;
//    public void sendMail(){
//
//        setMailServerProperties();
//
//
//
//        try
//        {
//            createEmailMessage();
//            sendEmail();
//        }
//        catch (AddressException e)
//        {
//            System.out.println("Address Exception:" + e.getMessage());
//            e.printStackTrace();
//        }
//        catch (MessagingException e)
//        {
//            System.out.println("Message Exception:" + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//    public void setMailServerProperties()
//    {
//
//        String emailPort = "587";// gmail's smtp port
//
//        emailProperties = System.getProperties();
//        emailProperties.put("mail.smtp.port", emailPort);
//        emailProperties.put("mail.smtp.auth", "true");
//        emailProperties.put("mail.smtp.starttls.enable", "true");
//
//    }
//
//    public void createEmailMessage() throws AddressException,
//            MessagingException
//    {
//        String[] toEmails =
//                { "531546130@qq.com" };
//        String emailSubject = "Java Email";
//        String emailBody = "This is an email sent by <b>JavaMail</b> api.";
//
//        mailSession = Session.getDefaultInstance(emailProperties, null);
//        emailMessage = new MimeMessage(mailSession);
//
//
//
//        for (int i = 0; i < toEmails.length; i++)
//        {
//            emailMessage.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(toEmails[i]));
//        }
//
//        emailMessage.setSubject(emailSubject);
//
//        emailMessage.setContent(emailBody, "text/html");// for a html email
//        MimeMessageHelper helper = new MimeMessageHelper(emailMessage, true);
//
//
//        String path = "D:\\unimelb最后一学期\\softwareProject\\backend\\henfan\\Rapid_feedback_backend\\test.pdf";
//        FileSystemResource file  = new FileSystemResource(new File(path));
//
//        helper.addAttachment("test.pdf",file);
//        // emailMessage.setText(emailBody);// for a text email
//
//    }
//
//    public void sendEmail() throws AddressException, MessagingException
//    {
//
//        String emailHost = "smtp.gmail.com";
//        String fromUser = "yutingcaicyt@gmail.com";// just the id alone without
//        // @gmail.com
//        String fromUserEmailPassword = "199522cyt@";
//
//        Transport transport = mailSession.getTransport("smtp");
//
//        transport.connect(emailHost, fromUser, fromUserEmailPassword);
//        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
//        transport.close();
//        System.out.println("Email sent successfully.");
//    }
//




    @Autowired
    private JavaMailSenderImpl mailSender;

    private static final String SENDER = "ycca1@student.unimelb.edu.au";

    private static final String FILEName = "presentation_assessment.pdf";

    public void sendMimeMessage(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource file  = new FileSystemResource(new File(filePath));
            helper.addAttachment(FILEName,file);
            String emailPort = "587";// gmail's smtp port

            Properties emailProperties = System.getProperties();
            emailProperties.put("mail.smtp.port", emailPort);
            emailProperties.put("mail.smtp.auth", "true");
            emailProperties.put("mail.smtp.starttls.enable", "true");
            mailSender.setJavaMailProperties(emailProperties);
            mailSender.send(message);
        }catch (MessagingException e){
            log.info("there is a error when sending the email to {}",to);
        }
    }
}
