package br.eti.inovareti.bluefood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BlueFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueFoodApplication.class, args);
    }

}
