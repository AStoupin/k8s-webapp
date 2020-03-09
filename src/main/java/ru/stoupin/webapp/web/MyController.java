package ru.stoupin.webapp.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	private static final Log LOG = LogFactory.getLog(MyController.class); 

	MyController(){
		LOG.info("MyController Created. To check the Rest use http://localhost:8080/k8s-webapp");
	}
	
	
	@RequestMapping("/")
	public @ResponseBody String hello() {
		
		LOG.info("hello hit");
		return "Hello app";
	}

}
