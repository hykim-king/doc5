package com.project.doc5.board;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.doc5.board.domain.BoardVO;
import com.project.doc5.board.service.BoardService;
import com.project.doc5.board.service.BoardServiceImpl;
import com.project.doc5.cmn.DTO;
import com.project.doc5.mapper.BoardMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class BoardServiceTest {
	
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	ApplicationContext  context;
	
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
		
		int seq = 0;
		board01 = new BoardVO(seq, "제목1", "내용1",0,"작성일 null","admin","등록일 null","admin");
		board02 = new BoardVO(seq, "제목1", "내용1",0,"작성일 null","admin","등록일 null","admin");
		board03 = new BoardVO(seq, "제목1", "내용1",0,"작성일 null","admin","등록일 null","admin");
		
		dto = new DTO();
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──tearDown────────────────│");
		log.debug("└──────────────────────────┘");	
	}
	
	@Test
	void doSelectOneUp() {
		log.debug("┌──────────────────────────┐");
		log.debug("│──doSelectOneUp           │");
		log.debug("└──────────────────────────┘");
		
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체 삭제
		// 2. 단건등록(Board01)
		
		// 3-1. 조회전 : 단건조회 : boardMapper view_cnt == 0
		// 3-2. 조회후 : 단건조회 : boardService view_cnt == 1
		
		// 4. 조회카운트 : view_cnt+1 확	
		
		//1.
		boardMapper.deleteAll();
		int count = boardMapper.getCount();
		assertEquals(0, count);	
				
		//2.
		int flag = boardService.doSave(board01);
		log.debug("flag:{}",flag);
		assertEquals(1, flag);
		
		//3-1.
		BoardVO outVO = boardMapper.doSelectOne(board01);
		log.debug("outVO : {}",outVO);
		assertEquals(0, outVO.getViewCnt());
		
		//3-2.
		BoardVO outVOReadCnt = boardService.upDoSelectOne(outVO);
		log.debug("outVOReadCnt : {}",outVOReadCnt);
		assertEquals(1, outVOReadCnt.getViewCnt());
				
		
//		boardMapper.updateReadCnt(board01);
		
		
		
	}
	
	@Disabled
	@Test
	void beans() {
		log.debug("┌──────────────────────────┐");
		log.debug("│──beans                   │");
		log.debug("└──────────────────────────┘");
		
		log.debug("context:"+context);
		assertNotNull(context);
		
		log.debug("boardMapper:"+boardMapper);
		assertNotNull(boardMapper);
		
		log.debug("boardService:"+boardService);
		assertNotNull(boardService);
	}

}
