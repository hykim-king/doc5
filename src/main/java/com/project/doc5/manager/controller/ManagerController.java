package com.project.doc5.manager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.doc5.branch.domain.BranchVO;
import com.project.doc5.manager.domain.AdDTO;
import com.project.doc5.manager.domain.AdOrderVO;
import com.project.doc5.manager.service.AdService;
import com.project.doc5.mypage.domain.MypageOrderVO;
import com.project.doc5.user.domain.UserVO;
import com.project.doc5.user.service.UserService;

// package 및 import는 생략

@Controller
@RequestMapping("/manager")
public class ManagerController {

	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	private AdService adService;
	
	@Autowired
	UserService userService;
	

	private static final String BRANCH_CODE = "s0001";

	public ManagerController() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│ManagerController()       │");
		log.debug("└──────────────────────────┘");
	}

	@RequestMapping(value = "/index.do")
	public String index(Locale locale, Model model) {

		// 1. 지점명 조회
		BranchVO branchInfo = adService.getBranchName(BRANCH_CODE);
		model.addAttribute("adminBranchName", branchInfo);

		// 2. 월 매출 및 주문 수 조회
		AdOrderVO salesInfo = adService.getSalesAndOrderCount(BRANCH_CODE);
		model.addAttribute("salesStatus", salesInfo);

		return "/manager/index";
	}

	@RequestMapping(value = "/member.do")
	public String member(Model model, @RequestParam(value = "searchWord", required = false) String searchWord) {

		// 1. 지점명 조회 (우측 상단 출력을 위해 필요)
		BranchVO branchInfo = adService.getBranchName(BRANCH_CODE);
		model.addAttribute("adminBranchName", branchInfo);

		// 2. 회원 목록 조회 (검색 조건 적용)
		List<UserVO> memberList = adService.getMemberList(searchWord);
		model.addAttribute("memberList", memberList);

		// 3. 검색 결과 유지를 위해 검색어를 모델에 담아 JSP로 전달
		model.addAttribute("searchWord", searchWord);

		return "/manager/member";
	}

	// 회원 삭제 처리 (POST 요청으로 분리)
	@PostMapping(value = "/deleteMember.do")
	public String deleteMember(@RequestParam("userId") String userId) {
		// 4. 회원 및 카트 데이터 삭제 (deleteMemberWithCart)
		adService.deleteMemberWithCart(userId);

		// 처리 후 회원 목록 페이지로 리다이렉트
		return "redirect:/manager/member.do";
	}
	
		
	@RequestMapping(value = "/shopDetail.do")
	public String shopDetail(@RequestParam("orderNo") String orderNo, Model model) {

		// 1. 지점명 조회 (우측 상단 출력을 위해 필요)
		BranchVO branchInfo = adService.getBranchName(BRANCH_CODE);
		model.addAttribute("adminBranchName", branchInfo);

		log.debug("orderNo : {}", orderNo);

    	
    	MypageOrderVO mypageOrderVO = new MypageOrderVO();
    	mypageOrderVO.setOrderNo(orderNo);
    	
    	List<MypageOrderVO> list = adService.doDetailOrder(mypageOrderVO);
		
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
		
		return "/manager/shop_detail";
	}
	
	@RequestMapping(value = "/shop.do")
	public String shop(Model model) {

		// 1. 지점명 조회 (우측 상단 출력을 위해 필요)
		BranchVO branchInfo = adService.getBranchName(BRANCH_CODE);
		model.addAttribute("adminBranchName", branchInfo);

		// 2. 지점별 전체 주문 목록 조회 (getOrdersByBranch)
		List<AdOrderVO> allOrders = adService.getOrdersByBranch(BRANCH_CODE);
		model.addAttribute("allOrders", allOrders);

		AdDTO dto = new AdDTO();
		dto.setBranchCode(BRANCH_CODE);

		// 3. 지점별 미처리 주문 조회 (getPendingOrders)
		List<AdOrderVO> pendingOrders = adService.getPendingOrders(dto);
		model.addAttribute("pendingOrders", pendingOrders);

		return "/manager/shop";
	}

	// 주문 처리 (POST 요청으로 분리)
	@PostMapping(value = "/processOrder.do")
	public String processOrder(@RequestParam("orderNo") String orderNo, @RequestParam("step") String step) {

		// 7. 주문 상태 변경 처리 (processOrder)
		int result = adService.processOrder(orderNo, BRANCH_CODE, step);
		log.debug("주문 처리 결과: {}건", result);

		// 처리 후 주문 목록 페이지로 리다이렉트
		return "redirect:/manager/shop.do";
	}

	@RequestMapping(value = "/branchLogin.do")
	public String login(Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│branchLogin()             │");
		log.debug("└──────────────────────────┘");

		return "/manager/login";

	}
	
	@PostMapping(value = "/doLoginCheck.do")
	@ResponseBody
	public String doLoginCheck(UserVO param, HttpServletResponse response, HttpSession session) throws IOException  {
		log.debug("┌──────────────────────────┐");
		log.debug("│doLoginCheck()            │");
		log.debug("└──────────────────────────┘");

		String message = "";
		
		log.debug("1.param:{}",param);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			UserVO userVO = userService.userLogin(param);
			log.debug("3.userVO : {}", userVO);
			
			session.setAttribute("sessionUser", userVO);
			message = "로그인 되었습니다.";
			
			out.println("<script type='text/javascript'>");
			out.println("alert('" + message + "');"); 
			out.println("parent.location.href='../manager/index.do'"); 
			out.println("</script>");
			out.flush();
			return null;
			
		} catch (RuntimeException e) {
			message = e.getMessage();
			out.println("<script type='text/javascript'>");
			out.println("alert('" + message + "');"); 
			out.println("</script>");
			out.flush();
			return null;
		}

	}
	
	
}