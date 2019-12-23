package edu.xd.bdilab.iotplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(
        exclude = {SecurityAutoConfiguration.class }
)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableWebMvc
public class IotplatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotplatformApplication.class, args);
    }

}
