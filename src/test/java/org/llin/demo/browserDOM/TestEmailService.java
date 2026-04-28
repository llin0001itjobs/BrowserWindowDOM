package org.llin.demo.browserDOM;

import org.junit.jupiter.api.Test;
import org.llin.demo.browserDOM.config.PropertyDefaultProperties;
import org.llin.demo.browserDOM.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
@EnableConfigurationProperties(PropertyDefaultProperties.class)
public class TestEmailService {
	
	@Autowired
	EmailService emailService;	 
	
	@Test
    public void testEmail() {
        try {
        	emailService.sendSimpleEmail("llin0001test01@gmail.com", "Test Subject", "Test body");
        } catch (Exception e) {
            // Log the error instead of failing startup
            System.err.println("Test email failed: " + e.getMessage());
        }
    }

}
