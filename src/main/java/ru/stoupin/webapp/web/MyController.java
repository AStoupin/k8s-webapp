package ru.stoupin.webapp.web;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	private static final Log LOG = LogFactory.getLog(MyController.class); 
	
	private String env;
	
	MyController(@Value("${env}") String env ){
		LOG.info("MyController Created. To check the Rest use http://localhost:8080/k8s-webapp");
		this.env = env;
		
	}
	
	
	@RequestMapping("/")
	public @ResponseBody String hello() {
		
		LOG.info("hello hit");
		return String.format("Hello app  %s on %s ", getVersion(), env);
	}

	
	public synchronized String getVersion() {
	    String version = null;

	    // try to load from maven properties first
	    try {
	        Properties p = new Properties();
	        InputStream is = getClass().getResourceAsStream("/META-INF/maven/ru.stoupin.webapp/webapp/pom.properties");
	        if (is != null) {
	            p.load(is);
	            version = p.getProperty("version", "");
	        }
	    } catch (Exception e) {
	        // ignore
	    }

	    // fallback to using Java API
	    if (version == null) {
	        Package aPackage = getClass().getPackage();
	        if (aPackage != null) {
	            version = aPackage.getImplementationVersion();
	            if (version == null) {
	                version = aPackage.getSpecificationVersion();
	            }
	        }
	    }

	    if (version == null) {
	        // we could not compute the version so use a blank
	        version = "";
	    }

	    return version;
	} 	
}
