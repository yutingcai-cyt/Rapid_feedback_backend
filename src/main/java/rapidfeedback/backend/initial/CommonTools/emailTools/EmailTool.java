package rapidfeedback.backend.initial.CommonTools.emailTools;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BlobByteObjectArrayTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.CommonTools.Exception.CommonError;
import rapidfeedback.backend.initial.CommonTools.Exception.FBException;
import rapidfeedback.backend.initial.functionality.email.model.AudioRequest;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.sql.Blob;
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

    private static final String AUDIO = "Audio.wav";

    private static final String AUDIOPATH ="/tmp/audio.wav";
    //private static final String AUDIOPATH ="D:\\unimelb最后一学期\\softwareProject\\backend\\henfan\\Rapid_feedback_backend\\audio.wav";
    public void sendMimeMessage(List<String> to, String subject, String content, String filePath, AudioRequest audioRequest){
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
            //pdf
            FileSystemResource file  = new FileSystemResource(new File(filePath));
            helper.addAttachment(FILEName,file);
            //audio
            FileOutputStream ops;
            if(audioRequest == null){
                log.info("there is no audio for this student");
            }else{
                File a = new File(AUDIOPATH);
//                Blob b = (Blob)audio;
//                is = b.getBinaryStream();
                ops = new FileOutputStream(a);
//                byte[] buffer = new byte[1024];
//                int len = 0;
//                while ((len = is.read(buffer)) != -1){
//                    ops.write(buffer,0,len);
//                }
                ops.write(audioRequest.getBin_data());
                helper.addAttachment(AUDIO,a);
                ops.close();
            }

            String emailPort = "587";// gmail's smtp port

            Properties emailProperties = System.getProperties();
            emailProperties.put("mail.smtp.port", emailPort);
            emailProperties.put("mail.smtp.auth", "true");
            emailProperties.put("mail.smtp.starttls.enable", "true");
            mailSender.setJavaMailProperties(emailProperties);
            mailSender.send(message);
        }catch (Exception e){
            log.info("there is a error {} when sending the email to {}",e,to);
            throw new FBException(CommonError.INTERNAL_SERVER_ERROR.getResultCode(),"there is a error when sending the email");
        }
    }
}
