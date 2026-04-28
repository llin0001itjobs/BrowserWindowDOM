package org.llin.demo.browserDOM.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils; // Add this import
import org.apache.commons.lang3.StringUtils; // Optional, for trimming
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
        
    @Value("${spring.mail.username}")
    private String emailFrom;
       
    @PostConstruct
    private void init() {
    	//emailFrom = config.getPropertyDefaultProperties().getSpring().getMail().getUsername();
    }
    

    
    // Send a simple text email
    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    // Send an HTML email (for verification links)
    public void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(emailFrom);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(message);
    }
	
	  // New: Convert plain text to basic HTML (escaping + formatting + optional URL links)
    public String convertToHtml(String plainText) {
        if (StringUtils.isBlank(plainText)) {
            return "";
        }

        // Step 1: Escape HTML special characters
        String escaped = StringEscapeUtils.escapeHtml4(plainText);

        // Step 2: Handle newlines (simple: single to <br>, double to <p>)
        String formatted = escaped.replaceAll("(?<!\n)\n(?!\n)", "<br>")
                                 .replaceAll("\n\n", "</p><p>")
                                 .replaceFirst("^<p>", "<p>") // Wrap in <p> if starting with double newline
                                 .replaceFirst("</p>$", "</p>"); // Ensure closing if needed

        // Step 3: Optional - Auto-link URLs (using regex)
        String urlPattern = "\\b(https?://\\S+|www\\.[\\w.-]+\\.[a-z]{2,}\\S*)";
        Pattern pattern = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(formatted);
        return matcher.replaceAll(match -> {
            String url = match.group();
            if (!url.startsWith("http")) {
                url = "http://" + url;
            }
            return "<a href=\"" + url + "\">" + match.group() + "</a>";
        });
    }
}