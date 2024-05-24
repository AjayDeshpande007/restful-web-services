package com.ajay.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	
	
	/*
	 * This method is for static filtering
	 * @GetMapping("/filtering") public SomeBean filtering() { return new
	 * SomeBean("Value1", "Value2", "Value3"); }
	 */
	
	
	// This method is for Dynamic filtering
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		
		SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
		//For Dynamic Filtering
		MappingJacksonValue mappingJacksonvalue = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonvalue.setFilters(filters);
		
		return mappingJacksonvalue;
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> filteringList() {
		return Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value4", "Value5", "Value6")) ;
	}
}
