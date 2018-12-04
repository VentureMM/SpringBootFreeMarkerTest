package com.imooc.SpringBootFreeMarker.Web;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.SpringBootFreeMarker.Entity.User;

@Controller
@RequestMapping("/")
public class IndexController {
	//private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @GetMapping(value={"","/index"})
	public ModelAndView doIndex(){
		//LOGGER.info("doIndex method is run!");
		ModelAndView mv = new ModelAndView();
		mv.addObject("username", "你好,Freemarker");
		mv.addObject("test", this.getStr());
		mv.addObject("userList",this.prepareUserList());
		return mv;
	}
	public static List<User> prepareUserList() {
		List<User> list = new ArrayList<User>();
		for(int i=0;i<5;i++){
			User item = new User();
			item.setName("用户"+i);
			item.setBirthday(new Date());
			list.add(item);
		}
		return list;
	}
	public List<String> getStr(){
		List<String> list = new ArrayList<String>();
		list.add("lucy");
		list.add("lily");
		return list;
	}
	
}
