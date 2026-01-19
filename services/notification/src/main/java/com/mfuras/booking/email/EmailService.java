package com.mfuras.booking.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import org.thymeleaf.context.Context;
import java.util.HashMap;
import java.util.Map;

import static com.mfuras.booking.email.EmailTemplates.BOOKINGS_CONFIRMATION;
import static com.mfuras.booking.email.EmailTemplates.BRANCH_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendBranchConfirmation(
            String destinationEmail,
            String customerName,
            String bookingsReference
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());
        messageHelper.setFrom("mmgubo25@gmail.com");
        final String templateName = BRANCH_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("ordersReference", bookingsReference);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(BRANCH_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s,", destinationEmail, templateName));
        }
        catch (MessagingException e){
            log.warn("WARNING - Cannot send email to {}" , destinationEmail);
        }
    }

    @Async
    public void sendBookingConfirmation(
            String destinationEmail,
            String customerName,
            String bookingsReference
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());
        messageHelper.setFrom("mmgubo25@gmail.com");
        final String templateName = BOOKINGS_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("bookingsReference", bookingsReference);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(BOOKINGS_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s,", destinationEmail, templateName));
        }
        catch (MessagingException e){
            log.warn("WARNING - Cannot send email to {}" , destinationEmail);
        }
    }
}
