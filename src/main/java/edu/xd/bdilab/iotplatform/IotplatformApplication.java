package edu.xd.bdilab.iotplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class IotplatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotplatformApplication.class, args);
    }

}
