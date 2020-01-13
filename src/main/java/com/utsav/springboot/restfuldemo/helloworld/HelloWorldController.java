package com.utsav.springboot.restfuldemo.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path = "/hello")
	public String getMessage() {
		return "Hello world";
	}
	
	@GetMapping(path = "/hello-bean")
	public Helloworld getMessageFromBean() {
		return new Helloworld("Hello World from bean ");
	}
	
	@GetMapping(path = "/hello-bean/{name}")
	public Helloworld getMessageFromPathVariable(@PathVariable String name) {
		return new Helloworld("Hello world" + " " + name);
	}
	
	@GetMapping(path = "/hello-language")
	public String getMessageInternationalization() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
