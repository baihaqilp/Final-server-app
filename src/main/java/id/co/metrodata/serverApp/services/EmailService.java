package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.dto.request.EmailRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    ThymeleafService thymeleafService;
    public EmailRequest sendMailGrade(EmailRequest emailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Map<String, Object> variables = new HashMap<>();
            variables.put("name", emailRequest.getName());
            variables.put("email", emailRequest.getTo());
            helper.setText(thymeleafService.createContent("mail-grade.html", variables), true);
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            javaMailSender.send(message);

            System.out.println();
            System.out.println("Email success to send...");
            System.out.println();
            return emailRequest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emailRequest;
    }
    public EmailRequest sendMailRegister(EmailRequest emailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Map<String, Object> variables = new HashMap<>();
            variables.put("name", emailRequest.getName());
            variables.put("email", emailRequest.getTo());
            helper.setText(thymeleafService.createContent("mail-register.html", variables), true);
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            javaMailSender.send(message);

            System.out.println();
            System.out.println("Email success to send...");
            System.out.println();
            return emailRequest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emailRequest;
    }
}
