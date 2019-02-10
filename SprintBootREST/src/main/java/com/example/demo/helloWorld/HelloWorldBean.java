package com.example.demo.helloWorld;

public class HelloWorldBean {
	
	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public HelloWorldBean(String msg) {
		setMessage(msg);
	}

	
}
