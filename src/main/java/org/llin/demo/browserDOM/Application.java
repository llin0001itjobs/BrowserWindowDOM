package org.llin.demo.browserDOM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.llin.demo.browserDOM")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
