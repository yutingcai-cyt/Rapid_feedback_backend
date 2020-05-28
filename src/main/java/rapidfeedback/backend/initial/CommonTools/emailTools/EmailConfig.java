package rapidfeedback.backend.initial.CommonTools.emailTools;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author cyt
 * @date 2020/5/25
 */
@Configuration
public class EmailConfig {
    @Bean
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setUsername("ycca1@student.unimelb.edu.au");
        mailSender.setPassword("199522cyt@");
        mailSender.setPort(587);
        mailSender.setDefaultEncoding("utf-8");

        return mailSender;
    }
    @Bean
    public Properties emailProperties(){
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        return emailProperties;

    };
}
