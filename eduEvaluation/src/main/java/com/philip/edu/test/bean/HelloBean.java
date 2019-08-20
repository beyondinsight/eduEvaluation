package com.philip.edu.test.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HelloBean {
	private String hello;
	
	public HelloBean(String s){
		this.hello = s;
	}
	
	public HelloBean(){
		this.hello = "test";
	}

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

}
