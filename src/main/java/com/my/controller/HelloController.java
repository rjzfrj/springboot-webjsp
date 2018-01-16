package com.my.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public ModelAndView greeting(@RequestParam(value="name", required=false, defaultValue="World") String name ) {
    	ModelAndView mv = new ModelAndView("hello");
    	 mv.addObject("name",name );
        return mv;
    }
    
}
