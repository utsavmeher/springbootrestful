package com.utsav.springboot.restfuldemo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Utsav Meher");
	}
	
	@GetMapping("v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2("Rakesh","Meher");
	}
	
	@GetMapping(value = "person/param", params = "version=1")
	public PersonV1 getPersonparam1() {
		return new PersonV1("Utsav Meher");
	}
	
	@GetMapping(value = "person/param", params = "version=2")
	public PersonV2 getPersonparam2() {
		return new PersonV2("Rakesh","Meher");
	}
	
	@GetMapping(value = "person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonheader() {
		return new PersonV1("Utsav Meher");
	}
	
	@GetMapping(value = "person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonheader2() {
		return new PersonV2("Rakesh","Meher");
	}
	
	@GetMapping(value = "person/produces", produces = "application/v1+json")
	public PersonV1 getPersonproduces1() {
		return new PersonV1("Utsav Meher");
	}
	
	@GetMapping(value = "person/produces", produces = "application/v2+json")
	public PersonV2 getPersonproduces2() {
		return new PersonV2("Rakesh","Meher");
	}
}
