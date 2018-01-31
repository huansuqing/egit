package cn.e3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class PageController {


	@RequestMapping("{page}")
	//以下注解用来接收参数  
	public String showPage(@PathVariable String page){
		return page;
	}
}
