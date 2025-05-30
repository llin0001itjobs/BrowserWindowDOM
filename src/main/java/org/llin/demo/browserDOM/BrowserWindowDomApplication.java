package org.llin.demo.browserDOM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BrowserWindowDomApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BrowserWindowDomApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(BrowserWindowDomApplication.class, args);
	}

}
