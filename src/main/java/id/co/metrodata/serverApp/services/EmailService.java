package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.dto.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    ThymeleafService thymeleafService;

    public EmailRequest sendMailGrade(EmailRequest emailRequest, String username, String segment, String kelas, String program) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Map<String, Object> variables = new HashMap<>();
            variables.put("name", emailRequest.getName());
            variables.put("username", username);
            variables.put("segment", segment);
            variables.put("kelas", kelas);
            variables.put("program", program);
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

    public EmailRequest sendMailRegister(EmailRequest emailRequest, String username, String password, String program, String kelas) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Map<String, Object> variables = new HashMap<>();
            variables.put("name", emailRequest.getName());
            variables.put("username", username);
            variables.put("password", password);
            variables.put("program", program);
            variables.put("kelas", kelas);

            ClassPathResource image = new ClassPathResource("static/images/logolms.png");
            byte[] imageBytes = StreamUtils.copyToByteArray(image.getInputStream());
            String imageBase = Base64.getEncoder().encodeToString(imageBytes);
            variables.put("logo", imageBase);
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
