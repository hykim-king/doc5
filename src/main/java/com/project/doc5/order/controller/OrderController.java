package com.project.doc5.order.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.project.doc5.cmn.MessageVO;
import com.project.doc5.mypage.domain.MypageCartGoodsOptionVO;
import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.order.domain.OrderVO;
import com.project.doc5.order.service.OrderService;
import com.project.doc5.user.domain.UserVO;

@Controller
@RequestMapping("/order")
public class OrderController {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	OrderService orderService;
	
	
	@PostMapping(value = "/doOrder.do")
	@ResponseBody
	public String doOrder(
	        @RequestParam("seq") String seq,
	        @RequestParam String orderType,
	        @RequestParam double totalOptionPrice,
	        @RequestParam double totalGoodsPrice,
	        @RequestParam double totalPrice,
	        HttpSession session,
	        RedirectAttributes redirectAttr
	) {
	    log.debug("┌──────────────────────────┐");
	    log.debug("│doOrder()                 │");
	    log.debug("└──────────────────────────┘");

	    UserVO user = (UserVO) session.getAttribute("sessionUser");

	    MessageVO message = new MessageVO();
	    String jsonString = "";

	    if (user == null) {
	        message.setFlag(3);
	        message.setMessage("로그인 후 이용 가능합니다");
	        return new Gson().toJson(message);
	    }

	    try {
	    	List<Long> seqs = Arrays.stream(seq.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
	        List<MypageCartVO> cartList = orderService.doList(seqs, orderType);
	        
	        int goodsCnt = cartList.size();
	        String orderName;
	        
	        if (goodsCnt == 1) {
	            orderName = cartList.get(0).getGoodsName();
	        } else {
	            orderName = cartList.get(0).getGoodsName() + " 외 " + (goodsCnt - 1) + "개";
	        }
	        
	        // =========================
	        // OrderVO 생성
	        // =========================
	        OrderVO orderVO = new OrderVO();
	        orderVO.setBranchCode("s0001");
	        orderVO.setGoodsTotalCnt(goodsCnt);
	        orderVO.setGoodsTotalPrice(totalGoodsPrice);
	        orderVO.setOptionTotalPrice(totalOptionPrice);
	        orderVO.setSettleTotalPrice(totalPrice);
	        orderVO.setSettleKind("CARD");
	        orderVO.setOrderName(orderName);

	        
	        double serverTotalPrice = 0;

	        for (MypageCartVO vo : cartList) {
	            serverTotalPrice += vo.getTotalGoodsTotalPrice();
	        }

	        if (Math.abs(serverTotalPrice - totalPrice) > 1.0) {

	            message.setFlag(0);
	            message.setMessage("결제 금액이 일치하지 않습니다. 다시 시도해주세요.");

	            return new Gson().toJson(message);
	        }
	        
	        
	        // =========================
	        // 주문 저장
	        // =========================
	        int flag = orderService.doOrder(orderVO);
	        

	        if (flag == 1) {
	            
	        	for (MypageCartVO vo : cartList) {
	        	    orderService.updateCartOrderNo(
	        	        vo.getSeq(),
	        	        orderVO.getOrderNo()
	        	    );
	        	}
	        	
	        	
	        	message.setFlag(1);
	            message.setMessage("주문 성공");
	            message.setOrderNo(orderVO.getOrderNo());
	            
	        } else {
	            message.setFlag(0);
	            message.setMessage("주문 저장 실패");
	        }

	    } catch (Exception e) {
	        log.error("주문 처리 중 오류", e);
	        message.setFlag(0);
	        message.setMessage("주문 처리 중 오류가 발생했습니다");
	    }

	    
	    
	    
	    jsonString = new Gson().toJson(message);
	    return jsonString;
	}



	

	
	
	@PostMapping(value = "/order.do")  
	public String order(@RequestParam(required = false, defaultValue = "") 
			String seq, @RequestParam(required = false, defaultValue = "") 
			String orderType, HttpSession session, Model model) {
		
		log.debug("┌──────────────────────────┐");
		log.debug("│order()                   │");
		log.debug("└──────────────────────────┘");

		UserVO userVO = (UserVO) session.getAttribute("sessionUser");
		
		List<MypageCartVO> list = null;
		log.debug("seq : {}",seq);
		log.debug("orderType : {}",orderType);
		
		if(null != userVO) {
			
			List<Long> seqs = Arrays.stream(seq.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
			list = orderService.doList(seqs, orderType);
			
			
		}else {
			
		}
		log.debug("list"+list);
		model.addAttribute("list", list );
		model.addAttribute("orderType", orderType);
		model.addAttribute("headerFl", "N" );
		model.addAttribute("bottomMenuFl", "N" );

		return "/order/order";
	}
}