package com.project.doc5.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.doc5.mapper.MypageOrderMapper;
import com.project.doc5.mypage.domain.MypageOrderVO;
import com.project.doc5.mypage.service.MypageOrderService;
import com.project.doc5.user.domain.UserVO;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	MypageOrderService mypageOrderService;

	public MypageController() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│MypageController()        │");
		log.debug("└──────────────────────────┘");	
	}
	
	@GetMapping(value = "/myOrderList.do", produces = "text/plain;charset=UTF-8")
	public String myOrderList(final HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		log.debug("┌──────────────────────────┐");
		log.debug("│myOrderList()             │");
		log.debug("└──────────────────────────┘");
		
		String viewString = "mypage/mypage_order_list";
		
		HttpSession session = request.getSession();
		
		if(null == session.getAttribute("sessionUser")) {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();	
        	String message = "잘못된 접속입니다.";
	        out.println("<script>");
	        out.println("alert('"+message+"');");
	        out.println("location.href='/';"); //  페이지로 돌아가기
	        out.println("</script>");
	        out.flush();
	        return null;
        }else {
        	UserVO userVO = (UserVO) session.getAttribute("sessionUser");
        	String userId = userVO.getUserId();
        	
        	MypageOrderVO mypageOrderVO = new MypageOrderVO();
        	mypageOrderVO.setUserId(userId);
        	
        	List<MypageOrderVO> list = mypageOrderService.doRetrieve(mypageOrderVO);
    		for(MypageOrderVO vo  :list) {
    			log.debug(vo);
    		}
    		model.addAttribute("list",list);
        }
		return viewString;
	}
	
	@GetMapping(value = "/myOrderView.do", produces = "text/plain;charset=UTF-8")
	public String myOrderView(String orderNo, HttpSession session, HttpServletResponse response, Model model) throws IOException {
		
		log.debug("┌──────────────────────────┐");
		log.debug("│myOrderView()             │");
		log.debug("└──────────────────────────┘");
		
		String viewString = "mypage/mypage_order_view";
		
		log.debug("orderNo : {}", orderNo);
		
		if(null == session.getAttribute("sessionUser")) {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();	
        	String message = "잘못된 접속입니다.";
	        out.println("<script>");
	        out.println("alert('"+message+"');");
	        out.println("location.href='../';"); //  페이지로 돌아가기
	        out.println("</script>");
	        out.flush();
	        return null;
        }else {
        	UserVO userVO = (UserVO) session.getAttribute("sessionUser");
        	String userId = userVO.getUserId();
        	
        	MypageOrderVO mypageOrderVO = new MypageOrderVO();
        	mypageOrderVO.setUserId(userId);
        	mypageOrderVO.setOrderNo(orderNo);
        	
        	List<MypageOrderVO> list = mypageOrderService.doDetailOrder(mypageOrderVO);
    		
        	String orderStepText;
        	if(list.get(0).getOrderStep().equals("p")) {
        		orderStepText = "주문완료";
        	}else if(list.get(0).getOrderStep().equals("p")) {
        		
        	}
        	
        	switch (list.get(0).getOrderStep()) {
			case "O":
				orderStepText = "주문완료 되었습니다.";
				break;
			case "P":
				orderStepText = "준비중 입니다.";
				break;
			case "S":
				orderStepText = "준비완료(픽업) 되었습니다.";
				break;
			case "C":
				orderStepText = "주문취소 되었습니다.";
				break;

			default:
				orderStepText = "기타";
				break;
			}
        	log.debug("orderStepText : {}", orderStepText);
        	log.debug("MypageOrderVO : {}", list);
        	model.addAttribute("orderStepText",orderStepText);
    		model.addAttribute("list",list);
    		
    		model.addAttribute("headerFl", "N" );
    		model.addAttribute("bottomMenuFl", "N" );
        }
		return viewString;
	}
}
