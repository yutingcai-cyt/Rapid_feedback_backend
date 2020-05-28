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
import java.util.List;
import java.util.Properties;

/**
 * send email to others
 * @author cyt
 * @date 2020/5/15
 */

@Slf4j
@Service
public class EmailTool {



    @Autowired
    private JavaMailSenderImpl mailSender;

    private static final String SENDER = "ycca1@student.unimelb.edu.au";

    private static final String FILEName = "presentation_assessment.pdf";

    public void sendMimeMessage(List<String> to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();
        String[] toList = new String[to.size()];
        for(int i = 0; i<= to.size() - 1; i++){
            toList[i] = to.get(i);
        }
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER);
            helper.setTo(toList);
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
