package aiyunnet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Home/")
public class HomeController
{
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hello World");
		mv.setViewName("index");
		return mv;
	}
}
