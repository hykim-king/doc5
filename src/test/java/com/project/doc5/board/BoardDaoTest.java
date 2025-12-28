package com.project.doc5.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
import com.project.doc5.cmn.DTO;
import com.project.doc5.mapper.BoardMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class BoardDaoTest {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ApplicationContext  context;
	
	@Autowired
	BoardMapper boardMapper;
	
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
	void doRetrieve() {
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체 삭제
		// 2. 1002건 등록
		// 3. 페이징 조회
		// 4. 결과확인	
		
		//1.
		boardMapper.deleteAll();
		int count = boardMapper.getCount();
		assertEquals(0, count);	
		
		//2.
		count = boardMapper.saveAll();
		assertEquals(1002, count);	
	
		// 3.
		dto.setPageNo(1);
		dto.setPageSize(10);
		dto.setSearchDiv("10");
		dto.setSearchWord("제목2");
		
		List<BoardVO> list = boardMapper.doRetrieve(dto);
		for(BoardVO vo  :list) {
			log.debug(vo);
		}
		assertEquals(list.size(), 10);
		//총건수
		//assertEquals(1002, list.get(0).getTotalCnt());
		log.debug("총 건수:{}",list.get(0).getTotalCnt());
	}
	
	@Disabled
	@Test
	void doSave() {
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체 삭제
		// 2. 단건등록board01)	
		// 3. 전체건수 조회
		// 4. 실제데이터 조회
		
		// 1. 
		boardMapper.deleteAll();
		int count = boardMapper.getCount();
		assertEquals(0, count);
		
		
		// 2.
		int flag = boardMapper.doSave(board01);
		log.debug("flag : {}", flag);
		log.debug("board01 : {}", board01);
		
		count = boardMapper.getCount();
		assertEquals(1, count);
		
		BoardVO outVO = boardMapper.doSelectOne(board01);
		log.debug("outVO : {}",outVO);
		assertNotNull(outVO);
		
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
	void beans() {
		log.debug("context:"+context);
		assertNotNull(context);
		
		log.debug("boardMapper:"+boardMapper);
		assertNotNull(boardMapper);
	}

}
