package com.project.doc5.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.doc5.cmn.MenuVO;
import com.project.doc5.cmn.MessageVO;
import com.project.doc5.goods.domain.GoodsSearchKeywordVO;
import com.project.doc5.mapper.GoodsSearchMapper;
import com.project.doc5.user.domain.Grade;
import com.project.doc5.user.domain.UserVO;
import com.project.doc5.user.service.UserService;

import sun.invoke.empty.Empty;

@Controller
@RequestMapping("/user")
public class UserController {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	UserService userService;
	
	@Autowired
	GoodsSearchMapper goodsSearchMapper;
	
	public UserController() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│UserController()          │");
		log.debug("└──────────────────────────┘");	
	}
	
	
	@RequestMapping(value = "/userRegist.do")  
	public String userRegist(Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│userRegist()              │");
		log.debug("└──────────────────────────┘");

		String actionUrl = "/user/doSave.do";
		model.addAttribute("pageTitle","회원가입");
		model.addAttribute("actionUrl",actionUrl);
		return "/user/user_regist";
	}
	
	@RequestMapping(value = "/userModify.do")  
	public String userModify(HttpServletResponse response, final HttpServletRequest request, Model model) throws IOException {
		log.debug("┌──────────────────────────┐");
		log.debug("│userModify()              │");
		log.debug("└──────────────────────────┘");
		
		HttpSession session = request.getSession();
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();	
        
        String message;
        if(null == session.getAttribute("sessionUserId")) {
        	message = "잘못된 접속입니다.";
	        out.println("<script>");
	        out.println("alert('"+message+"');");
	        out.println("location.href='/';"); //  페이지로 돌아가기
	        out.println("</script>");
	        out.flush();
	        return null;
        }else {
        	String userId = session.getAttribute("sessionUserId")+"";
        	UserVO userVO = new UserVO();
        	userVO.setUserId(userId);
        	UserVO userInfo = userService.doSelectOne(userVO);
        	log.debug("userInfo : {}",userInfo);
        	model.addAttribute("userInfo",userInfo);
        	
        }
		
		String actionUrl = "/user/doModify.do";
		model.addAttribute("pageTitle","회원정보 수정");
		model.addAttribute("actionUrl",actionUrl);
		
		return "/user/user_regist";
	}
	
	/**
	 * 로그 아웃 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value =  "/doLogOut.do")
//	@ResponseBody
	public String doLogOut(HttpServletResponse response, final HttpServletRequest request) throws IOException {
		log.debug("┌──────────────────────────┐");
		log.debug("│doLogOut()                │");
		log.debug("└──────────────────────────┘");
		
		String message = "";
		
		HttpSession session = request.getSession();
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();	
        
		if(null != session.getAttribute("sessionUserId")) {
			
			session.setAttribute("sessionUserId",null);
			session.setAttribute("sessionUserName",null);
			
			message = "로그아웃 완료했습니다.";
	        out.println("<script>");
	        out.println("alert('"+message+"');");
	        out.println("location.href='/';"); //  페이지로 돌아가기
	        out.println("</script>");
	        out.flush();
	        return null; // 스크립트 출력이 끝났으므로 null 반환
		}else {
			message = "잘못된 접근입니다.";
			
	        out.println("<script>");
	        out.println("alert('"+message+"');");
	        out.println("history.back();"); // 이전 페이지로 돌아가기
	        out.println("</script>");
	        out.flush();
	        return null; // 스크립트 출력이 끝났으므로 null 반환
		}
	}
	
	/**
	 * 로그인 
	 * @param param
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value =  "/doLoginCheck.do",produces = "text/plain;charset=UTF-8")
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
			out.println("parent.location.href='/'"); 
			out.println("</script>");
			out.flush();
			
		} catch (RuntimeException e) {
			message = e.getMessage();
			out.println("<script type='text/javascript'>");
			out.println("alert('" + message + "');"); 
			out.println("</script>");
			out.flush();
		}
		

		return "";
		
	}
	
	@GetMapping(value = "/doSelectOne.do")  
	public String doSelectOne(@RequestParam(required = false, defaultValue = "99") String userId
			, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│doSelectOne()             │");
		log.debug("└──────────────────────────┘");
		log.debug("1.userId:{}",userId);
		
		UserVO param=new UserVO();
		param.setUserId(userId);
		
		UserVO outVO = userService.doSelectOne(param);
		model.addAttribute("user",outVO);

		
		return "user/user_mng";	
	}

	@GetMapping(value = "/viewDoSave.do")
	public String viewDoSave() {
		log.debug("┌──────────────────────────┐");
		log.debug("│viewDoSave()              │");
		log.debug("└──────────────────────────┘");	
		
		return "user/user_reg";
	}
	

	@PostMapping(value =  "/doSave.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(UserVO param, HttpServletResponse response, final HttpServletRequest request) throws IOException {
		log.debug("┌──────────────────────────┐");
		log.debug("│doSave()                  │");
		log.debug("└──────────────────────────┘");	
		
		param.setGrade(Grade.BASIC);
		param.setPrivacyDt("Y");
		
		log.debug("1.param:{}",param);
		
		UserVO userInfo = userService.doSelectOne(param);
		log.debug("2.userInfo:{}",userInfo);
		
		String message = "";
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(param.getPassword().length() < 5) {
			message = "비밀번호는 5자리 이상 입력해주세요.";
			
			out.println("<script type='text/javascript'>");
			out.println("alert('" + message + "');"); 
			out.println("</script>");
			out.flush();
		}else {
		
			if(userInfo == null) {
				int flag = userService.doSave(param);
				log.debug("2.flag:{}",flag);
		
				if( 1 == flag ) {
					message = param.getName()+"님 등록 되었습니다.";
					
					HttpSession session = request.getSession();
					session.setAttribute("sessionUserId", param.getUserId());// session에 'userId' 속성값 저장
					session.setAttribute("sessionUserName", param.getName());// session에 'name' 속성값 저장
					session.setMaxInactiveInterval(30*60); // 30분
				}else {
					message = param.getName()+"님 등록 실패 했습니다.";
					
				}
				out.println("<script type='text/javascript'>");
				out.println("alert('" + message + "');"); 
				out.println("parent.location.href='/'"); 
				out.println("</script>");
				out.flush();
			
			}else{
				
				message = "이미 등록된 정보 입니다.";
				out.println("<script type='text/javascript'>");
				out.println("alert('" + message + "');"); 
				out.println("</script>");
				out.flush();
				
			}
		}
		
		return "";
//		return "user/user_reg";
	}
	

	@PostMapping(value =  "/doModify.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doModify(UserVO param, @RequestParam("password_re") String passwordRe, HttpServletResponse response, final HttpServletRequest request) throws IOException {
		log.debug("┌──────────────────────────┐");
		log.debug("│doModify()                │");
		log.debug("└──────────────────────────┘");	
		
		
		
		log.debug("1.param:{}",param);
		log.debug("1.passwordRe:{}",passwordRe);
		
		UserVO userInfo = userService.doSelectOne(param);
		log.debug("2.userInfo:{}",userInfo);
		param.setGrade(userInfo.getGrade());
		param.setPrivacyDt("");
		
		String message = "";
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(param.getPassword().length() < 5) {
			message = "비밀번호는 5자리 이상 입력해주세요.";
			
			out.println("<script type='text/javascript'>");
			out.println("alert('" + message + "');"); 
			out.println("</script>");
			out.flush();
			return null;
		}else {
			
			if(param.getPassword().equals(passwordRe)) {
		
				int flag = userService.doUpdate(param);
				log.debug("2.flag:{}",flag);
		
				if( 1 == flag ) {
					message = param.getName()+"님 수정 되었습니다.";
					
					HttpSession session = request.getSession();
					session.setAttribute("sessionUserId", param.getUserId());// session에 'userId' 속성값 저장
					session.setAttribute("sessionUserName", param.getName());// session에 'name' 속성값 저장
					session.setMaxInactiveInterval(30*60); // 30분
				}else {
					message = param.getName()+"님 수정 실패 했습니다.";
				}
				out.println("<script type='text/javascript'>");
				out.println("alert('" + message + "');"); 
				out.println("parent.location.href='/'"); 
				out.println("</script>");
				out.flush();
				return null;
				
				
			}else {
				message = "비밀번호가 일치하지 않습니다.";
				out.println("<script type='text/javascript'>");
				out.println("alert('" + message + "');"); 
				out.println("</script>");
				out.flush();
				return null;
			}
		}
		
	}
	
}
