package com.project.doc5.manager.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
<<<<<<< Updated upstream
@RequestMapping("/manager")
=======
@RequestMapping("/admin")
>>>>>>> Stashed changes
public class ManagerController {

	final Logger log = LogManager.getLogger(getClass());

	public ManagerController() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│AdminController()         │");
		log.debug("└──────────────────────────┘");	
	}
	
	@RequestMapping(value = {"/", "/index"})
	public String index(Locale locale, Model model) {
		return "/manager/index"; 
	}
	
	@RequestMapping(value = "/member.do")
	public String member(Locale locale, Model model) {
		
		return "/manager/admin_member"; 
	}
	
	@RequestMapping(value = "/shop.do")
	public String shop(Locale locale, Model model) {
		
		return "/manager/admin_shop"; 
	}
	
	@RequestMapping(value = "/branchLogin.do")  
	public String login(Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│branchLogin()             │");
		log.debug("└──────────────────────────┘");

		return "/manager/login";
	}
	
	
}
