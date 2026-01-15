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
	
	@RequestMapping(value = "/findId.do")  
	public String findId(Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│findId()              │");
		log.debug("└──────────────────────────┘");

		String actionUrl = "./idFind.do";
		model.addAttribute("pageTitle","아이디찾");
		model.addAttribute("actionUrl",actionUrl);
		return "/user/find_password";
	}
	
	@RequestMapping(value = "/findPassword.do")  
	public String findPassword(Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│findPassword()              │");
		log.debug("└──────────────────────────┘");

		String actionUrl = "./passwordFindPs.do";
		model.addAttribute("pageTitle","비밀번호찾기");
		model.addAttribute("actionUrl",actionUrl);
		return "/user/find_password";
	}
	
	@RequestMapping(value = "/passwordFindPs.do")  
	public String passwordFindPs(Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│passwordFindPs()          │");
		log.debug("└──────────────────────────┘");

		
		return "/user/find_password";
	}
	
	@RequestMapping(value = "/certificationNumbeCheck.do")  
	@ResponseBody
	public String certificationNumbeCheck(String certificationNumbe, HttpSession session, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│certificationNumbeCheck() │");
		log.debug("└──────────────────────────┘");
		
		String jsonString = "";
		
		MessageVO message = new MessageVO();
		
		
		
		
		
		String sess_certificationNumber = (String) session.getAttribute("sessionCertificationNumbe");
		if(certificationNumbe == sess_certificationNumber) {
			message.setFlag(1);
			message.setMessage("");
		}else {
			message.setFlag(2);
		}
			
		
		//log.debug("certificationNumber : {}", certificationNumber);
		
		jsonString = new Gson().toJson(message);
		
		return jsonString;
	}
	
	@RequestMapping(value = "/certificationNumber.do")  
	@ResponseBody
	public String certificationNumber(
			@RequestParam("type") String type,
			@RequestParam(required = false) String findUserId,
			@RequestParam(required = false) String findPhone,
			@RequestParam(required = false) String findPassword,
			@RequestParam(required = false) String findPasswordRe,
			@RequestParam(required = false) String findCertificationNumber,
			HttpSession session, 
			Model model
		) {
		log.debug("┌──────────────────────────┐");
		log.debug("│certificationNumber()     │");
		log.debug("└──────────────────────────┘");
		
		String jsonString = "";
		
		MessageVO message = new MessageVO();
		UserVO userVO = new UserVO();
		
		if(type.equals("sendcode")) {
			userVO.setUserId(findUserId);
        	UserVO userInfo = userService.doSelectOne(userVO);
        	log.debug("userInfo : {}",userInfo);
        	
        	if(null == userInfo) {
        		message.setFlag(2);
    			message.setMessage(findUserId+"님 고객 정보가 없습니다.");
        	}else {
        		
        		String tmpUserPhone = userInfo.getPhone().replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9,. ]", "");
        		String tmpFindPhone = findPhone.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9,. ]", "");
        		log.debug("userPhone : {}",tmpUserPhone);

        		if(!tmpUserPhone.equals(tmpFindPhone)) {
        			message.setFlag(2);
        			message.setMessage(findUserId+"님 휴대폰번호가 일치하지 않습니다.");
        		}else {
	        		String certificationNumberCode = userService.certificationNumber();
	    			
	    			message.setFlag(1);
	    			message.setMessage(certificationNumberCode);
	    			
	    			session.setAttribute("sessionCertificationNumbe", certificationNumberCode);
	    			log.debug("certificationNumberCode : {}", certificationNumberCode);
        		}
        	}
        	
			
			
			
			
		}else if(type.equals("confirm")){
			
			String sessionCertificationNumbe = (String) session.getAttribute("sessionCertificationNumbe");
			
			if(findCertificationNumber.equals(sessionCertificationNumbe)) {
				message.setFlag(1);
				message.setMessage("인증번호가 확인 되었습니다.");
			}else {
				message.setFlag(2);
				message.setMessage("인증번호가 틀렸습니다. \n다시 확인해 주세요.");
			}
			log.debug("certificationNumber : {}",findCertificationNumber);
			log.debug("sessionCertificationNumbe : {}",sessionCertificationNumbe);
		}else if(type.equals("passwordChange")){
			
			userVO.setUserId(findUserId);

        	UserVO userInfo = userService.doSelectOne(userVO);
        	log.debug("userInfo : {}",userInfo);
        	
        	userInfo.setPassword(findPassword);
        	int flag = userService.doUpdate(userInfo);
			log.debug("2.flag:{}",flag);
			
			if( 1 == flag ) {
				message.setFlag(1);
				message.setMessage(userInfo.getName()+"님 비밀번호가 수정 되었습니다.");
			}else {
				message.setFlag(2);
				message.setMessage(userInfo.getName()+"님 비밀번호가 수정 실패했습니다.");
			}
        	
			
		}
				
		
		jsonString = new Gson().toJson(message);
		
		return jsonString;
	}
	

	
	@RequestMapping(value = "/userRegist.do")  
	public String userRegist(Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│userRegist()              │");
		log.debug("└──────────────────────────┘");

		String actionUrl = "./doSave.do";
		model.addAttribute("pageTitle","회원가입");
		model.addAttribute("actionUrl",actionUrl);
		return "/user/user_regist";
	}
	
	@RequestMapping(value = "/userModify.do")  
	public String userModify(HttpServletResponse response, HttpSession session, Model model) throws IOException {
		log.debug("┌──────────────────────────┐");
		log.debug("│userModify()              │");
		log.debug("└──────────────────────────┘");
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();	
        
        UserVO userVO = (UserVO) session.getAttribute("sessionUser");
        
        String message;
        if(null == userVO) {
        	message = "잘못된 접속입니다.";
	        out.println("<script>");
	        out.println("alert('"+message+"');");
	        out.println("location.href='./';"); //  페이지로 돌아가기
	        out.println("</script>");
	        out.flush();
	        return null;
        }else {
        	userVO.setUserId(userVO.getUserId());
        	UserVO userInfo = userService.doSelectOne(userVO);
        	log.debug("userInfo : {}",userInfo);
        	model.addAttribute("userInfo",userInfo);
        	
        }
		
		String actionUrl = "./doModify.do";
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
	public String doLogOut(HttpServletResponse response, HttpSession session) throws IOException {
		log.debug("┌──────────────────────────┐");
		log.debug("│doLogOut()                │");
		log.debug("└──────────────────────────┘");
		
		String message = "";
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();	
        
		if(null != session.getAttribute("sessionUser")) {
			
			session.invalidate();

			message = "로그아웃 완료했습니다.";
	        out.println("<script>");
	        out.println("alert('"+message+"');");
	        out.println("location.href='../';"); //  페이지로 돌아가기
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
			out.println("parent.location.href='../'"); 
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
	public String doSave(UserVO param, HttpServletResponse response, HttpSession session) throws IOException {
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
			return null;
		}else {
		
			if(userInfo == null) {
				
				param.setPhone(param.getPhone().replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9,. ]", ""));
				
				int flag = userService.doSave(param);
				log.debug("2.flag:{}",flag);
		
				if( 1 == flag ) {
					message = param.getName()+"님 등록 되었습니다.";

					UserVO userVO = userService.userLogin(param);
					log.debug("3.userVO : {}", userVO);
					
					session.setAttribute("sessionUser", userVO);
					session.setMaxInactiveInterval(30*60); // 30분
				}else {
					message = param.getName()+"님 등록 실패 했습니다.";
					
				}
				out.println("<script type='text/javascript'>");
				out.println("alert('" + message + "');"); 
				out.println("parent.location.href='../'"); 
				out.println("</script>");
				out.flush();
				return null;
			
			}else{
				
				message = "이미 등록된 정보 입니다.";
				out.println("<script type='text/javascript'>");
				out.println("alert('" + message + "');"); 
				out.println("</script>");
				out.flush();
				return null;
				
			}
		}
		

//		return "user/user_reg";
	}
	

	@PostMapping(value =  "/doModify.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doModify(UserVO param, @RequestParam("password_re") String passwordRe, HttpServletResponse response, HttpSession session) throws IOException {
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
		
		//비밀번호 입력없이 수정 하기
		if(param.getPassword().length() == 0 && passwordRe.length() == 0) {
			int flag = userService.doUpdateNotPass(param);
			log.debug("2.flag:{}",flag);
	
			if( 1 == flag ) {
				message = param.getName()+"님 수정 되었습니다.";
				session.setAttribute("sessionUser", userInfo);
			}else {
				message = param.getName()+"님 수정 실패 했습니다.";
			}
			out.println("<script type='text/javascript'>");
			out.println("alert('" + message + "');"); 
			out.println("parent.location.href='../'"); 
			out.println("</script>");
			out.flush();
			return null;
		}else {
		
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

						session.setAttribute("sessionUser", userInfo);
					}else {
						message = param.getName()+"님 수정 실패 했습니다.";
					}
					out.println("<script type='text/javascript'>");
					out.println("alert('" + message + "');"); 
					out.println("parent.location.href='../'"); 
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
	
	/**
	 * 회원탈퇴 
	 * @param param
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value =  "/ajaxCloseAccount.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String ajaxCloseAccount(UserVO param, HttpServletResponse response, HttpSession session){
		log.debug("┌──────────────────────────┐");
		log.debug("│ajaxCloseAccount()        │");
		log.debug("└──────────────────────────┘");
		
		String jsonString = null;
		MessageVO message = new MessageVO();
		UserVO userVO = (UserVO) session.getAttribute("sessionUser");
		
		message.setFlag(1);
		message.setMessage("회원탈퇴를 완료했습니다.");
		
		if (userVO.getUserId() != null) {
			param.setUserId(userVO.getUserId());
			int flag = userService.doDelete(param);
			session.invalidate();
			
			if(flag > 0) {
				message.setFlag(flag);
				message.setMessage("회원탈퇴를 완료했습니다.");
			}else {
				message.setFlag(flag);
				message.setMessage("회원탈퇴 실패했습니다.\n관리자에게 문의해주세요.");
			}
		}else {
			message.setFlag(2);
			message.setMessage("잘못된 접속입니다.");
		}
		
		jsonString = new Gson().toJson(message);
		
		return jsonString;
	}
	
}
