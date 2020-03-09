package ru.stoupin.webapp.web;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.stoupin.webapp.service.ModuleInfoService;

@Controller
public class MyController {
	private static final Log LOG = LogFactory.getLog(MyController.class); 
	
	private String env;
	private ModuleInfoService moduleInfoService;
	
	MyController(@Value("${env}") String env, ModuleInfoService moduleInfoService ){
		LOG.info("MyController Created. To check the Rest use http://localhost:8080/k8s-webapp");
		
		this.env = env;
		this.moduleInfoService = moduleInfoService;
		
	}
	
	
	@RequestMapping("/")
	public @ResponseBody String hello() {
		
		LOG.info("hello hit");
		return String.format("Hello App  %s on %s ", moduleInfoService.getVersion(), env);
	}

	

}
