package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// to tell this is request handling controller
@Controller // mandatory // if controller singleton & eager
public class HelloController {

	public HelloController() {
		System.out.println("In ctor of " + getClass().getName());
	}

	// Add request handling method : is handle request
	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println("In say hello");
		return "/welcome";
	}

}
