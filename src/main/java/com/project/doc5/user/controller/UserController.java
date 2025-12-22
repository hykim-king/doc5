package com.project.doc5.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.doc5.cmn.MessageVO;
import com.project.doc5.user.domain.UserVO;
import com.project.doc5.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	UserService userService;
	
	
	public UserController() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│UserController()          │");
		log.debug("└──────────────────────────┘");	
	}
	
	@PostMapping(value =  "/doLoginCheck.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doLoginCheck(UserVO param, HttpServletResponse response, final HttpServletRequest request) throws IOException {
		log.debug("┌──────────────────────────┐");
		log.debug("│doLoginCheck()            │");
		log.debug("└──────────────────────────┘");
		log.debug("1.param:{}",param);
		
		
		log.debug("loginUser : {}", param);
		UserVO userCheckVO=userService.doUserLogin(param);
		log.debug("userCheckVO : {}", userCheckVO);
		
		String message = "";
		
		if(null != userCheckVO.getUserId()) {
			
			// 로그인 시 세션 저장
			HttpSession session = request.getSession();
			session.setAttribute("userId", userCheckVO.getUserId());// session에 'userId' 속성값 저장
			session.setMaxInactiveInterval(30*60); // 30분

			String sessionUserId = (String) session.getAttribute("userId");
			log.debug("┌──────────────────────────┐");
			log.debug("│sessionUserId()           │"+sessionUserId);
			log.debug("└──────────────────────────┘");
			message = userCheckVO.getName() + "님 로그인 완료했습니다.";
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>");
	        out.println("alert('"+message+"');");
	        out.println("location.href='/';"); //  페이지로 돌아가기
	        out.println("</script>");
	        out.flush();
	        return null; // 스크립트 출력이 끝났으므로 null 반환
		}else {
			message = param.getUserId() + " 아이디가 없거나 비밀번호가 틀렸습니다.";
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>");
	        out.println("alert('"+message+"');");
	        out.println("history.back();"); // 이전 페이지로 돌아가기
	        out.println("</script>");
	        out.flush();
	        return null; // 스크립트 출력이 끝났으므로 null 반환
		}
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
	
	//JSON -> JSON데이터
	@PostMapping(value =  "/doSave.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(UserVO param) {
		log.debug("┌──────────────────────────┐");
		log.debug("│doSave()                  │");
		log.debug("└──────────────────────────┘");	
		
		String viewName = "user/user_mng";
		
		log.debug("1.param:{}",param);
		
		int flag = userService.doSave(param);
		log.debug("2.flag:{}",flag);

		String message = "";
		if( 1 == flag ) {
			message = param.getName()+"님 등록 되었습니다.";
		}else {
			message = param.getName()+"님 등록 실패 했습니다.";
		}
		
		MessageVO messageVO=new MessageVO();
		messageVO.setFlag(flag);
		messageVO.setMessage(message);
		
		log.debug("3.messageVO:{}",messageVO);
		log.debug("4.viewName:{}",viewName);
		Gson gson = new Gson();
		String jsonString = gson.toJson(messageVO);
		log.debug("4.jsonString:{}\n",jsonString);
		
		
		return jsonString;
	}
	
}
