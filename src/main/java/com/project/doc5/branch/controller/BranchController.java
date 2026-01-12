package com.project.doc5.branch.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.doc5.branch.domain.BranchVO;
import com.project.doc5.branch.service.BranchService;

@Controller
@RequestMapping("/branch")
public class BranchController {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	BranchService branchService;
	
	@GetMapping(value = "/selectBranch.do" )
	public String selectBranch(Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│selectBranch              │");
		log.debug("└──────────────────────────┘");
		
		
		return "/storefinder/storefinder";
	}
	
	@PostMapping(value = "/ajaxSelectBranch.do" )
	@ResponseBody
	public String ajaxSelectBranch(Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│selectBranch              │");
		log.debug("└──────────────────────────┘");
		
		List<BranchVO> branchList = branchService.searchCoordinate();
		log.debug("branchList size = {}", branchList.size());
		
		
		String jsonString;
		jsonString = new Gson().toJson(branchList);
		log.debug("jsonString : {}", jsonString);
		
		
		return jsonString;
	}
	
	
}
