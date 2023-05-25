package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.dto.request.EmailRequest;
import id.co.metrodata.serverApp.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/grade")
    public EmailRequest sendEmailGrade(@RequestBody EmailRequest emailRequest) {
        return emailService.sendMailGrade(emailRequest);
    }

    @PostMapping("/register")
    public EmailRequest sendEmailRegister(@RequestBody EmailRequest emailRequest) {
        return emailService.sendMailRegister(emailRequest);
    }
}
