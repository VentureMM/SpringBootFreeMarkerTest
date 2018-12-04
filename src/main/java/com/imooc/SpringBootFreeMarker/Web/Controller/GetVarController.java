package com.imooc.SpringBootFreeMarker.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class GetVarController {
@GetMapping("/getVar")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("getVar");
		mv.addObject("intVar",100);
		mv.addObject("longVar", 10000L);
		mv.addObject("booleanVar",false);
		mv.addObject("nullVar");
		return mv;
	}
	
}
