package com.project.doc5.branch.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/geoCoding.do")
	@ResponseBody
	public Map<String, Object> geoCoding(
	        @RequestParam String address
	) throws Exception {

	    String apiKey = "DBBDD515-A8B0-3BFF-BBAD-4C0F71172A74";
	    String epsg = "epsg:4326";

	    String urlStr =
	        "https://api.vworld.kr/req/address" +
	        "?service=address" +
	        "&request=getCoord" +
	        "&format=json" +
	        "&crs=" + epsg +
	        "&type=ROAD" +
	        "&key=" + apiKey +
	        "&address=" + URLEncoder.encode(address, "UTF-8");

	    URL url = new URL(urlStr);
	    BufferedReader br = new BufferedReader(
	            new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)
	    );

	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = br.readLine()) != null) {
	        sb.append(line);
	    }

	    // 그대로 JSON 반환 (프론트에서 확인용)
	    return new Gson().fromJson(sb.toString(), Map.class);
	}
	
	
}
