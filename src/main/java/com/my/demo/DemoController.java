package com.my.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DemoController {

	@ResponseBody
	@RequestMapping("/test")
	public String categoryList(String test) {
		System.out.println(test);
		return test;
	}

	@RequestMapping("/index")
	public String index(String test) {
		return "index";
	}

}
