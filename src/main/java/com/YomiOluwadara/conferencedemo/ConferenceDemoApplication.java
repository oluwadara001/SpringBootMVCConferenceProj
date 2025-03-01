package com.YomiOluwadara.conferencedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class ConferenceDemoApplication {

    public static void main(String[] args) {
		/*
			Use the run method to start the Spring application class called ConferenceDemoApplication
			 * Sets up the Spring Application Context.
    		* Configures any embedded web server (e.g., Tomcat, Jetty, Undertow) if you're building a web application.
    		* Scans for Spring components (like `@Component`, `@Service`, `@Controller`, `@Repository`).
    		* Initializes all Spring beans.
    		* Starts the web server (if applicable).
    		* Dispatches events that you can listen to.
    		* And a lot of other low level configurations to get the application ready.
			* This method is blocking.  It means that after it's called, your application will configure any
			 web servers, for ex, Tomcat
		 */
        SpringApplication.run(ConferenceDemoApplication.class, args);
    }
}

