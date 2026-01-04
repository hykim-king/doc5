package com.project.doc5.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

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
import com.project.doc5.board.domain.BoardVO;
import com.project.doc5.board.service.BoardService;
import com.project.doc5.cmn.DTO;
import com.project.doc5.cmn.MessageVO;
import com.project.doc5.mapper.BoardMapper;


@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class BoardControllerTest {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대역 객체
	MockMvc mockMvc;	
	
	@Autowired
	BoardMapper boardMapper;
	
	@Autowired
	BoardService boardService;
	
	BoardVO board01;
	BoardVO board02;
	BoardVO board03;
	
	DTO dto;
	
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──setup───────────────────│");
		log.debug("└──────────────────────────┘");	
		

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		int seq = 0;
		board01 = new BoardVO(seq, "제목01", "내용01",0,"작성일 null","admin","등록일 null","admin","");
		board02 = new BoardVO(seq, "제목02", "내용02",0,"작성일 null","admin","등록일 null","admin","");
		board03 = new BoardVO(seq, "제목03", "내용03",0,"작성일 null","admin","등록일 null","admin","");
		
		dto    = new DTO();	
	}


	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──tearDown────────────────│");
		log.debug("└──────────────────────────┘");	
		
		
	}

	@Disabled
	@Test
	void doSelectOne() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──doSelectOne             │");
		log.debug("└──────────────────────────┘");
		
		//1. 전체삭제 :Mapper
		//2. 등록 :BoarService
		//3. 한건조회  : BoarService
		
		//1.
		boardMapper.deleteAll();
		int count = boardMapper.getCount();
		assertEquals(0, count);
		
		//2.
		this.boardService.doSave(board01);
		count = boardMapper.getCount();
		assertEquals(1, count);
		
		//2. method:post, url: /board/doDelete.do, param: BoardVO
		MockHttpServletRequestBuilder requestBuilder = 
				MockMvcRequestBuilders.get("/board/doSelectOne.do")
				.param("seq", board01.getSeq()+"");
				
		//2.2 controller 호
		ResultActions resultActions =  mockMvc.perform(requestBuilder).andExpect(status().isOk());
				
		//2.3 호출결과 받기 
		Map<String, Object> model = resultActions.andDo(print()).andReturn().getModelAndView().getModel();
		//String jsonResult = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		
		BoardVO outVO = (BoardVO) model.get("vo");
		log.debug("1. outVO:{}",outVO);
		
		isSameData(outVO, board01);
	}
	
	private void isSameData(BoardVO outVO01, BoardVO board01) {
		assertEquals(outVO01.getSubject(), board01.getSubject());
		assertEquals(outVO01.getContents(), board01.getContents());
		assertEquals(outVO01.getRegId(), board01.getRegId());
		assertEquals(outVO01.getModId(), board01.getModId());
	}
	
	@Disabled
	@Test
	void doDelete() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──doDelete                │");
		log.debug("└──────────────────────────┘");
		
		//1. 전체삭제 :Mapper
		//2. 등록 :BoarService
		//3. 한건삭제 : BoarService
		
		boardMapper.deleteAll();
		int count = boardMapper.getCount();
		assertEquals(0, count);
		
		this.boardService.doSave(board01);
		count = boardMapper.getCount();
		assertEquals(1, count);
		
		//2. method:post, url: /board/doDelete.do, param: BoardVO
		MockHttpServletRequestBuilder requestBuilder = 
				MockMvcRequestBuilders.post("/board/doDelete.do")
				.param("seq", board01.getSeq()+"");
		
		//2.2 controller 호
		ResultActions resultActions =  mockMvc.perform(requestBuilder).andExpect(status().isOk());
		
		//2.3 호출결과 받기 
		String jsonResult = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		log.debug("1. jsonResult:{}",jsonResult);
	}
	
	@Disabled
	@Test
	void doSave() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──doSave                  │");
		log.debug("└──────────────────────────┘");
		
		//1. 전체삭제
		//2. 등록 : BoardService
				
		boardMapper.deleteAll();
		int count = boardMapper.getCount();
		assertEquals(0, count);
		
		//2. method:post, url: /board/dosave.do, param: BoardVO
		MockHttpServletRequestBuilder requestBuilder = 
				MockMvcRequestBuilders.post("/board/doSave.do")
				.param("subject", board01.getSubject())
				.param("contents", board01.getContents())
				.param("regId", board01.getRegId())
				.param("modId", board01.getModId())
				;
		
		//2.2 controller 호출 
		ResultActions resultActions =  mockMvc.perform(requestBuilder).andExpect(status().isOk());
		
		//2.3 호출결과 받기 
		String jsonResult = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		log.debug("1. jsonResult:{}",jsonResult);
		
		MessageVO messageVO = new Gson().fromJson(jsonResult, MessageVO.class);
		log.debug("2. messageVO:{}",messageVO);
		
		assertEquals(1, messageVO.getFlag());
	}

	@Disabled
	@Test
	void beans() {
		log.debug("┌──────────────────────────┐");
		log.debug("│──beans                   │");
		log.debug("└──────────────────────────┘");
		
		log.debug("webApplicationContext:{}",webApplicationContext);
		assertNotNull(webApplicationContext);
		
		log.debug("mockMvc:{}",mockMvc);
		assertNotNull(mockMvc);
		
		log.debug("boardMapper:{}",boardMapper);
		assertNotNull(boardMapper);
		
		log.debug("boardService:{}",boardService);
		assertNotNull(boardService);
		
		
	}

}
