package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@EntityScan(basePackages = { "model" })
@ComponentScan(basePackages = { "dao", "controller", "config" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
