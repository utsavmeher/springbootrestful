package com.utsav.springboot.restfuldemo.helloworld;

public class Helloworld {

	private String message;

	public Helloworld() {

	}

	public Helloworld(final String name) {
		this.message = name;
	}

	public String getName() {
		return message;
	}

	public void setName(String name) {
		this.message = name;
	}
}
