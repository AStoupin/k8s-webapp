package ru.stoupin.webapp.web;

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
		String sys1=System.getenv("system1");
		return String.format("Hello App  %s on %s  with system %s", moduleInfoService.getVersion(), env, sys1);
	}

	@RequestMapping("/bad")
	public @ResponseBody String bad() {
		
		LOG.info("bad hit");
	
		Runtime.getRuntime().halt(100);
		return "never return";
	}	
	

}
