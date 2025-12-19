package com.project.doc5.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.project.doc5.cmn.DTO;
import com.project.doc5.cmn.MessageVO;
import com.project.doc5.mapper.UserMapper;
import com.project.doc5.user.domain.Grade;
import com.project.doc5.user.domain.UserVO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class UserControllerTest {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대역 객체
	MockMvc mockMvc; 
	
	@Autowired
	UserMapper userMapper;
	
	UserVO user01;
	UserVO user02;
	UserVO user03;
	
	DTO dto;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──setup───────────────────│");
		log.debug("└──────────────────────────┘");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		user01 = new UserVO("pcwk01_김", "4321a", "이상무01","010-0000-0000",Grade.BASIC,"날짜 없음!","NULL","NULL");		
		user02 = new UserVO("pcwk02_김", "4321a", "이상무02","010-0000-0000",Grade.SILVER,"날짜 없음!","NULL","NULL");
		user03 = new UserVO("pcwk03_김", "4321a", "이상무03","010-0000-0000",Grade.GOLD,"날짜 없음!","NULL","NULL");
		
		dto = new DTO();
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│─tearDown()               │");
		log.debug("└──────────────────────────┘");	
	}

	@Test
	void doSave() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│─doSave()                 │");
		log.debug("└──────────────────────────┘");	
		
		//1. 전체삭제
		//2. 등록: Conroller 호출
		
		//1.
		userMapper.deleteAll();
		//userMapper.doSave(param);
		
		//2.1 url, method, param
		MockHttpServletRequestBuilder requestBuilder 
			= MockMvcRequestBuilders.post("/user/doSave.do")
			.param("userId", user01.getUserId())
			.param("name", user01.getName())
			.param("password", user01.getPassword())
			.param("phone", user01.getPhone())
			.param("grade", user01.getGrade()+"")
			;
		
		//2.2 Controller 호출
		ResultActions resultActions =  mockMvc.perform(requestBuilder)
		.andExpect(status().isOk());
		
		//2.3 호출결과 받기
		String jsonResult = resultActions.andDo(print())
		.andReturn().getResponse().getContentAsString();
		;
		
		//2.4
		log.debug("jsonResult:{}",jsonResult);
		//2.4 Json -> MessageVO
		
		MessageVO messageVO=new Gson().fromJson(jsonResult, MessageVO.class);
		log.debug("3.messageVO:{}",messageVO);
		
		assertEquals(1, messageVO.getFlag());
		
	}
	
	
	@Disabled
	@Test
	void beans() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─beans()                  │");
		log.debug("└──────────────────────────┘");	
		
		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
	}

}
