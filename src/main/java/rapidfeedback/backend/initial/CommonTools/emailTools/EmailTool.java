package rapidfeedback.backend.initial.CommonTools.emailTools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * send email to others
 * @author cyt
 * @date 2020/5/15
 */

@Slf4j
@Service
public class EmailTool {

    @Autowired
    private  JavaMailSender mailSender;

    private static final String SENDER = "15221570989@163.com";

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

            mailSender.send(message);
        }catch (MessagingException e){
            log.info("there is a error when sending the email to {}",to);
        }
    }
}
