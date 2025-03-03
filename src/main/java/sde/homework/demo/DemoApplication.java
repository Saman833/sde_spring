package sde.homework.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sde.homework.demo.controllers.web.WebSubscribeController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		SpringApplication.run(WebSubscribeController.class, args);
	}

}
