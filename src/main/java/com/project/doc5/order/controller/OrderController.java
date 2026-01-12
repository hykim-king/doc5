package com.project.doc5.order.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.order.service.OrderService;
import com.project.doc5.user.domain.UserVO;

@Controller
@RequestMapping("/order")
public class OrderController {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	OrderService orderService;
	
	@PostMapping(value = "/order.do")  
	public String order(@RequestParam(required = false, defaultValue = "") String seq, @RequestParam(required = false, defaultValue = "") String orderType, HttpSession session, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│order()                   │");
		log.debug("└──────────────────────────┘");

		UserVO userVO = (UserVO) session.getAttribute("sessionUser");
		
		List<MypageCartVO> list = null;
		log.debug("seq : {}",seq);
		log.debug("orderType : {}",orderType);
		
		if(null != userVO) {
			MypageCartVO mypageCartVO = new MypageCartVO();
			
			list = orderService.doList(seq, orderType);
			
			
			
			
			
		}else {
			
		}
		
		model.addAttribute("list", list );
		model.addAttribute("headerFl", "N" );
		model.addAttribute("bottomMenuFl", "N" );

		return "/order/order";
	}
}
