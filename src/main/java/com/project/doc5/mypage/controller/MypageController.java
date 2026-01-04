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

import com.project.doc5.mapper.MypageOrderMapper;
import com.project.doc5.mypage.domain.MypageOrderVO;
import com.project.doc5.user.domain.UserVO;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	MypageOrderMapper mypageOrderMapper;

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
		
		if(null == session.getAttribute("sessionUserId")) {
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
        	
        	String userId = session.getAttribute("sessionUserId")+"";
        	
        	MypageOrderVO mypageOrderVO = new MypageOrderVO();
        	mypageOrderVO.setUserId(userId);
        	
        	List<MypageOrderVO> list = mypageOrderMapper.doRetrieve(mypageOrderVO);
    		for(MypageOrderVO vo  :list) {
    			log.debug(vo);
    		}
    		model.addAttribute("list",list);
        }
		return viewString;
	}
	
	@GetMapping(value = "/myOrderView.do", produces = "text/plain;charset=UTF-8")
	public String myOrderView(final HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		log.debug("┌──────────────────────────┐");
		log.debug("│myOrderView()             │");
		log.debug("└──────────────────────────┘");
		
		String viewString = "mypage/mypage_order_view";
		
		HttpSession session = request.getSession();
		
		if(null == session.getAttribute("sessionUserId")) {
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
        	
        	String userId = session.getAttribute("sessionUserId")+"";
        	
        	MypageOrderVO mypageOrderVO = new MypageOrderVO();
        	mypageOrderVO.setUserId(userId);
        	
        	List<MypageOrderVO> list = mypageOrderMapper.doRetrieve(mypageOrderVO);
    		for(MypageOrderVO vo  :list) {
    			log.debug(vo);
    		}
    		model.addAttribute("list",list);
        }
		return viewString;
	}
}
