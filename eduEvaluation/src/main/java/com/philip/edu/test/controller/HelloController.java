package com.philip.edu.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.philip.edu.test.bean.HelloBean;
import com.philip.edu.test.service.HelloService;

@RestController
@EnableWebMvc
public class HelloController {
	private static final String template = "Hello, %s!";
	
	@Autowired
	private HelloService service;
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<HelloBean> greeting(@RequestParam(value="name", defaultValue="World1") String name){
		//ApplicationContext context = request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//ApplicationContext context = ApplicationContextRegister.getApplicationContext();
		//HelloService service = (HelloService)context.getBean("service_test");
		String password = service.getPwByUserNm(name);
		
		return new ResponseEntity<HelloBean>(new HelloBean(new String().format(template, password)), HttpStatus.OK);
	}
}
