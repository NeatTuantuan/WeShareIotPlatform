package edu.xd.bdilab.iotplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(
        exclude = {SecurityAutoConfiguration.class }
)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class IotplatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotplatformApplication.class, args);
    }

}
