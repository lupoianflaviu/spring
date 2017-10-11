package ro.sci.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hotel Management
 *
 * @author Andrei Vintila, Darius Petricele, Flaviu Lupoian, Tudor Radovici
 * @version 1.1
 */
@SpringBootApplication
@ComponentScan("ro.sci.hotel")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}
