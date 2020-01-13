package com.utsav.springboot.restfuldemo.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.utsav.springboot.restfuldemo.filter.bean.Somebean;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public Somebean retrieveSomebean() {
		return new Somebean("value1", "value2", "value3");
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveSomebeanlist() {
		List<Somebean> list = Arrays.asList(new Somebean("value1", "value2", "value3"), new Somebean("value4", "value5", "value6"));
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomebeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}

	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue retrieveSomebeanDynamic() {
		Somebean somebean = new Somebean("value1", "value2", "value3");
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(somebean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomebeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
}
