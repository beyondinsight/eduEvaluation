package com.philip.edu.test.bean;

import java.util.List;


public class HelloBean {
	
	public static void main(String[] args){
		String a = "1.2.3";
		String[] temp = a.split("\\.");
		System.out.println(temp.length);
		for(int i=0; i<temp.length; i++){
			System.out.println(temp[i]);
		}
	}
	
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
