package br.com.expirationNotificationRobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class ExpirationNotificationRobotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpirationNotificationRobotApplication.class, args);
	}

}
