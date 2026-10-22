package eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@RequestMapping(value={"/home","/"},method = RequestMethod.GET)
	public String home() 
	{
		return "home";
	}
	
	@RequestMapping(value="/home",method = RequestMethod.POST)
	public String connect(@RequestParam(required=true) String login,String password,Model model, HttpSession session) 
	{
		model.addAttribute("role","admin");
		session.setAttribute("login", login);
		return "menu";
	}
}
