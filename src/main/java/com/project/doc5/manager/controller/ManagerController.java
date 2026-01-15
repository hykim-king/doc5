package com.project.doc5.manager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.doc5.branch.domain.BranchVO;
import com.project.doc5.manager.domain.AdDTO;
import com.project.doc5.manager.domain.AdOrderVO;
import com.project.doc5.manager.service.AdService;
import com.project.doc5.mypage.domain.MypageOrderVO;
import com.project.doc5.user.domain.UserVO;
import com.project.doc5.user.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	private AdService adService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/index.do")
	public String index(HttpSession session, Model model) {
		// 세션에서 지점코드 가져오기
		
		log.debug("세션 지점코드: " + session.getAttribute("adminBranchCode"));
		
		String branchCode = (String) session.getAttribute("adminBranchCode");
		if (branchCode == null) return "redirect:/manager/branchLogin.do";

		
		
		BranchVO branchInfo = adService.getBranchName(branchCode);
		model.addAttribute("adminBranchName", branchInfo);

		AdOrderVO salesInfo = adService.getSalesAndOrderCount(branchCode);
		model.addAttribute("salesStatus", salesInfo);

		return "/manager/index";
	}

	@RequestMapping(value = "/member.do")
	public String member(HttpSession session, Model model, @RequestParam(value = "searchWord", required = false) String searchWord) {
		String branchCode = (String) session.getAttribute("adminBranchCode");
		if (branchCode == null) return "redirect:/manager/branchLogin.do";

		BranchVO branchInfo = adService.getBranchName(branchCode);
		model.addAttribute("adminBranchName", branchInfo);

		List<UserVO> memberList = adService.getMemberList(searchWord);
		model.addAttribute("memberList", memberList);
		model.addAttribute("searchWord", searchWord);

		return "/manager/member";
	}

	@PostMapping(value = "/deleteMember.do")
	public String deleteMember(@RequestParam("userId") String userId) {
		adService.deleteMemberWithCart(userId);
		return "redirect:/manager/member.do";
	}
	
	@RequestMapping(value = "/shopDetail.do")
	public String shopDetail(HttpSession session, @RequestParam("orderNo") String orderNo, Model model) {
		String branchCode = (String) session.getAttribute("adminBranchCode");
		if (branchCode == null) return "redirect:/manager/branchLogin.do";

		BranchVO branchInfo = adService.getBranchName(branchCode);
		model.addAttribute("adminBranchName", branchInfo);

		MypageOrderVO mypageOrderVO = new MypageOrderVO();
		mypageOrderVO.setOrderNo(orderNo);
		
		List<MypageOrderVO> list = adService.doDetailOrder(mypageOrderVO);
		
		String orderStepText = "";
		if (list != null && !list.isEmpty()) {
			switch (list.get(0).getOrderStep()) {
				case "O": orderStepText = "주문완료 되었습니다."; break;
				case "P": orderStepText = "준비중 입니다."; break;
				case "S": orderStepText = "준비완료(픽업) 되었습니다."; break;
				case "C": orderStepText = "주문취소 되었습니다."; break;
				default:  orderStepText = "기타 상태"; break;
			}
		}
		
		model.addAttribute("orderStepText", orderStepText);
		model.addAttribute("list", list);
		
		return "/manager/shop_detail";
	}
	
	@RequestMapping(value = "/shop.do")
	public String shop(HttpSession session, AdDTO dto, Model model) {
	    String branchCode = (String) session.getAttribute("adminBranchCode");
	    dto.setBranchCode(branchCode);

	    int totalPending = adService.getCountPO(branchCode); 
	    setupPaging(dto, totalPending); // 페이징 계산

	    model.addAttribute("pendingOrders", adService.getPendingOrders(dto));
	    model.addAttribute("vo", dto); 
	    model.addAttribute("adminBranchName", adService.getBranchName(branchCode));
	    return "/manager/shop";
	}

	@RequestMapping(value = "/allOrders.do")
	public String allOrders(HttpSession session, AdDTO dto, Model model) {
	    String branchCode = (String) session.getAttribute("adminBranchCode");
	    dto.setBranchCode(branchCode);

	    int totalAll = adService.getCountAll(branchCode); 
	    setupPaging(dto, totalAll); // 페이징 계산

	    model.addAttribute("allOrders", adService.getOrdersByBranch(dto));
	    model.addAttribute("allVo", dto); 
	    model.addAttribute("adminBranchName", adService.getBranchName(branchCode));
	    return "/manager/shop_all_order";
	}

	private void setupPaging(AdDTO dto, int totalCount) {
	    int pageSize = 10;
	    int pageNo = (dto.getPageNo() <= 0) ? 1 : dto.getPageNo();
	    dto.setPageNo(pageNo);
	    dto.setPageSize(pageSize);

	    int lastPage = (int) Math.ceil((double) totalCount / pageSize);
	    int navSize = 5;
	    int endNo = (int) (Math.ceil((double) pageNo / navSize) * navSize);
	    int startNo = endNo - (navSize - 1);
	    if (endNo > lastPage) endNo = (lastPage == 0) ? 1 : lastPage;

	    dto.setStartNo(startNo);
	    dto.setEndNo(endNo);
	    dto.setPre(startNo > 1);
	    dto.setNext(endNo < lastPage);
	}
	@PostMapping(value = "/processOrder.do")
	public String processOrder(HttpSession session, @RequestParam("orderNo") String orderNo, @RequestParam("step") String step) {
		String branchCode = (String) session.getAttribute("adminBranchCode");
		
		int result = adService.processOrder(orderNo, branchCode, step);
		log.debug("주문 처리 결과: {}건", result);

		return "redirect:/manager/shop.do";
	}

	@RequestMapping(value = "/branchLogin.do")
	public String login() {
		return "/manager/login";
	}
	
	@PostMapping(value = "/doLoginCheck.do")
	@ResponseBody
	public void doLoginCheck(UserVO param, HttpServletResponse response,HttpServletRequest request, HttpSession session) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			UserVO userVO = userService.userLogin(param);
			
			// 세션에 유저 전체 정보와 지점 코드를 따로 저장
			session.setAttribute("sessionUser", userVO);
			session.setAttribute("adminBranchCode", userVO.getBranchCode()); 
			
			out.println("<script type='text/javascript'>");
			out.println("alert('로그인 되었습니다.');"); 
			
			
			String contextPath = request.getContextPath(); // 결과: /doc5
			out.println("window.top.location.href='" + contextPath + "/manager/index.do';");
			out.println("</script>");
			
		} catch (RuntimeException e) {
			out.println("<script type='text/javascript'>");
			out.println("alert('" + e.getMessage() + "');"); 
			out.println("</script>");
		} finally {
			out.flush();
			out.close();
		}
	}
}