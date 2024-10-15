package com.example.demo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(EmailJob.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Executing EmailJob - Sending email...");

        try {
            sendEmail();
        } catch (Exception e) {
            logger.error("Failed to send email", e);
        }
    }

    private void sendEmail() {
        logger.debug("Preparing to send email...");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("sudharsanan747@gmail.com");
        message.setSubject("Scheduled Email");
        message.setText("This is a scheduled email sent by Quartz job.");
        message.setFrom("sudharsanan747@gmail.com");

        logger.debug("Email message prepared: To={}, Subject={}", message.getTo()[0], message.getSubject());

        try {
            mailSender.send(message);
            logger.info("Email message sent to {}", message.getTo()[0]);
        } catch (Exception e) {
            logger.error("Error sending email to {}: {}", message.getTo()[0], e.getMessage(), e);
        }
    }
}
