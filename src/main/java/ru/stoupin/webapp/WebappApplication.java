package ru.stoupin.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages ="ru.stoupin.webapp.web")
@PropertySource(ignoreResourceNotFound=true, value= {"classpath:k8s-webapp.properties","file:${jboss.server.config.dir}/k8s-webapp.properties"})

public class WebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

}
