package com.project.doc5.admin.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	final Logger log = LogManager.getLogger(getClass());

	public AdminController() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│AdminController()         │");
		log.debug("└──────────────────────────┘");	
	}
	
	@RequestMapping(value = {"/", "/index"})
	public String index(Locale locale, Model model) {
		return "/admin/index"; 
	}
	
	@RequestMapping(value = "/member.do")
	public String member(Locale locale, Model model) {
		
		return "/admin/admin_member"; 
	}
	
	@RequestMapping(value = "/shop.do")
	public String shop(Locale locale, Model model) {
		
		return "/admin/admin_shop"; 
	}
	
	@RequestMapping(value = "/branchLogin.do")  
	public String login(Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│branchLogin()             │");
		log.debug("└──────────────────────────┘");

		return "/admin/login";
	}
	
	
}
