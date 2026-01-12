package com.project.doc5.cart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.project.doc5.cart.domain.CartVO;
import com.project.doc5.cart.service.CartService;
import com.project.doc5.cmn.MenuVO;
import com.project.doc5.cmn.MessageVO;
import com.project.doc5.mapper.CartMapper;
import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.user.domain.UserVO;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	CartService cartService;
	
	@GetMapping(value = "/cart.do")  
	public String cart(HttpSession session, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│cart()                    │");
		log.debug("└──────────────────────────┘");

		UserVO userVO = (UserVO) session.getAttribute("sessionUser");
		
		List<MypageCartVO> list = null;
		
		if(null != userVO) {
			MypageCartVO mypageCartVO = new MypageCartVO();
			
			mypageCartVO.setUserId(userVO.getUserId());
			list = cartService.doCartList(mypageCartVO);
			log.debug("list : {}",list);
			
		}else {
			
		}
		
		model.addAttribute("list", list );
		model.addAttribute("bottomMenuFl", "N" );

		return "/cart/cart";
	}
	
	@PostMapping(value = "/cartPs.do")
	@ResponseBody
	public String cartPs(@RequestParam(required = false) String type, @RequestParam(required = false) String unitSeq, @RequestParam(required = false , defaultValue = "0") int cnt ,HttpSession session) {
		log.debug("┌──────────────────────────┐");
		log.debug("│cartPs()                  │");
		log.debug("└──────────────────────────┘");

		UserVO userVO = (UserVO) session.getAttribute("sessionUser");
		
		
		log.debug("type : {}", type);
		log.debug("unitSeq : {}", unitSeq);
		log.debug("cnt : {}", cnt);
		
		MessageVO message = new MessageVO();
		String jsonString = "";
		int flag = 0;
		MypageCartVO mypageCartVO = new MypageCartVO();
		
		if(null != userVO) {
		
			if(type.equals("delete")) {
				message.setFlag(1);
				message.setMessage("");
				
				
				// 1. 쉼표로 구분된 문자열을 배열로 변환
				String[] unitSeqs = unitSeq.split(",");
				
				// 2. 개수와 상관없이 루프를 돌며 처리 (단건이면 1번만 실행됨)
				for (String seq : unitSeqs) {
				    if (seq != null && !seq.trim().isEmpty()) {
				        // VO에 각각의 seq 설정
				        mypageCartVO.setSeq(Integer.parseInt(seq)); 
				        
				        log.debug("삭제 대상 seq : {}", seq);
				        
				        // 3. 실제 삭제 로직 호출 (하나씩 삭제)
				        flag = cartService.doCartDelete(mypageCartVO);
				    }
				}
				
				message.setFlag(1);
				message.setMessage("삭제 성공");
				
			}else if(type.equals("update")){
				mypageCartVO.setSeq(Integer.parseInt(unitSeq));
				mypageCartVO.setGoodsCnt(cnt);
				mypageCartVO.setUserId(userVO.getUserId());
				
				log.debug("mypageCartVO : {}", mypageCartVO);
				flag = cartService.doCartUpdate(mypageCartVO);
				
				message.setFlag(flag);
				message.setMessage("수정 성공");
			}
		}else {
			message.setFlag(3);
			message.setSeq(0);
			message.setMessage("로그인 후 이용 가능합니다");
		}
		
		jsonString = new Gson().toJson(message);
		
		return jsonString;
	
	}
	

}
