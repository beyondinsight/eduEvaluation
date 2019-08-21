package com.philip.edu.test.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HelloBean {
	private String hello;
	private List<String> list;
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

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
