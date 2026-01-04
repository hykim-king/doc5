package com.project.doc5.mypage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.project.doc5.mapper.MypageCartMapper;
import com.project.doc5.mapper.MypageOrderMapper;
import com.project.doc5.mypage.domain.MypageCartGoodsOptionVO;
import com.project.doc5.mypage.domain.MypageCartVO;
//import com.project.doc5.mypage.domain.MypageCartGoodsOptionVO;
//import com.project.doc5.mypage.domain.MypageCartVO;
//import com.project.doc5.mypage.domain.MypageOrderVO;
import com.project.doc5.mypage.domain.MypageOrderVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class MypageDaoTest {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	MypageOrderMapper mypageOrderMapper;

	@Autowired
	MypageCartMapper mypageCartMapper;
	
	

	MypageCartVO mypageCartVO01;
	MypageCartVO mypageCartVO02;
	MypageCartVO mypageCartVO03;
	MypageCartVO mypageCartVO04;
	
	MypageOrderVO mypageOrderVO01;
	MypageOrderVO mypageOrderVO02;
	MypageOrderVO mypageOrderVO03;
	MypageOrderVO mypageOrderVO04;
	

	
	DTO dto;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──setup───────────────────│");
		log.debug("└──────────────────────────┘");	
		// SELECT TO_CHAR(SYSTIMESTAMP,'YYYYMMDDHH24MISSFF') FROM dual;
		
		// SETTEL_KIND 
		// CA = 카드, 
		// BA = 뱅크, 
		// ST = 스탬프, 
		// COUPON = 쿠폰
		
		// ORDER_STEP
		// O = 주문완료,
		// P = 준비중,
		// S =준비완료(픽업), 
		// C = 주문취소
		List<MypageCartGoodsOptionVO> mcgList = null;
		mypageOrderVO01 = new MypageOrderVO("20251227093030123456","s0001","","아메리카노",1,1700.00,1700.00,
				0.00,"BA","C","2025-12-27 09:30:30","2025-12-27 09:30:50","");
	
		mypageOrderVO02 = new MypageOrderVO("20251228123030654321","s0001","","아메리카노",1,2000.00,2000.00,
				0.00,"BA","S","2025-12-28 12:30:30","2025-12-28 12:30:50","");
		
		mypageOrderVO03 = new MypageOrderVO("20251229093030836592","s0001","","아메리카노",1,2000.00,2000.00,
				0.00,"BA","P","2025-12-29 09:30:30","2025-12-29 09:30:50","");
		
		mypageOrderVO04 = new MypageOrderVO("20251229113030333333","s0001","","아메리카노",1,2000.00,2000.00,
				0.00,"BA","O","2025-12-29 11:30:30","2025-12-29 11:30:50","");
		
		int seq = 0;
		mypageCartVO01 = new MypageCartVO(seq, 10000, "s0001", "20251227093030123456", "doc5_1@doc5.com",
				"아메리카노", 1700.00, 1, "N", "Y", 0.00, "N", 300.00,
				"2025-12-27 09:30:50", "2025-12-27 09:30:30", "2025-12-27 09:30:50", mcgList);
		
		mypageCartVO02 = new MypageCartVO(seq, 10000, "s0001", "20251228123030654321", "doc5_1@doc5.com",
				"아메리카노", 1700.00, 1, "N", "Y", 0.00, "N", 300.00,
				null, "2025-12-28 12:30:30", "2025-12-28 12:30:50", mcgList);
		
		mypageCartVO03 = new MypageCartVO(seq, 10000, "s0001", "20251229093030836592", "doc5_1@doc5.com",
				"아메리카노", 1700.00, 1, "N", "Y", 0.00, "N", 300.00,
				null, "2025-12-29 09:30:30", "2025-12-29 09:30:50", mcgList);
		
		mypageCartVO04 = new MypageCartVO(seq, 10000, "s0001", "20251229113030333333", "doc5_1@doc5.com",
				"아메리카노", 1700.00, 1, "N", "Y", 0.00, "N", 300.00,
				null, "2025-12-29 11:30:30", "2025-12-29 11:30:50", mcgList);
		
		dto = new DTO();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──tearDown────────────────│");
		log.debug("└──────────────────────────┘");	
	}
	
	
	// 주문 리스트
	//@Disabled
	@Test
	void doRetrieve() {
		log.debug("┌──────────────────────────┐");
		log.debug("│──doRetrieve()            │");
		log.debug("└──────────────────────────┘");	
		
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체 삭제
		// 2. 4건 등록
		// 3. 아이디 설정
		// 4. 주문 리스트 확인
		
		// 1. 
		mypageOrderMapper.deleteAll();
		int count = mypageOrderMapper.getCount();
		assertEquals(0, count);
		
		mypageCartMapper.deleteAll();
		count = mypageCartMapper.getCount();
		assertEquals(0, count);
		
		
		// 2-1
		int flag = mypageCartMapper.doSave(mypageCartVO01);
		log.debug("flag : {}", flag);
		log.debug("mypageCartVO01 : {}", mypageCartVO01);
		
		flag = mypageCartMapper.doSave(mypageCartVO02);
		log.debug("flag : {}", flag);
		log.debug("mypageCartVO02 : {}", mypageCartVO02);
		
		flag = mypageCartMapper.doSave(mypageCartVO03);
		log.debug("flag : {}", flag);
		log.debug("mypageCartVO03 : {}", mypageCartVO03);
		
		flag = mypageCartMapper.doSave(mypageCartVO04);
		log.debug("flag : {}", flag);
		log.debug("mypageCartVO04 : {}", mypageCartVO04);
		
		// 2-2
		count = mypageCartMapper.getCount();
		assertEquals(4, count);
				
		// 2-3
		flag = mypageOrderMapper.doSave(mypageOrderVO01);
		log.debug("flag : {}", flag);
		log.debug("mypageOrderVO01 : {}", mypageOrderVO01);
		
		flag = mypageOrderMapper.doSave(mypageOrderVO02);
		log.debug("flag : {}", flag);
		log.debug("mypageOrderVO02 : {}", mypageOrderVO02);
		
		flag = mypageOrderMapper.doSave(mypageOrderVO03);
		log.debug("flag : {}", flag);
		log.debug("mypageOrderVO03 : {}", mypageOrderVO03);
		
		flag = mypageOrderMapper.doSave(mypageOrderVO04);
		log.debug("flag : {}", flag);
		log.debug("mypageOrderVO04 : {}", mypageOrderVO04);
				
		// 2-4
		count = mypageOrderMapper.getCount();
		assertEquals(4, count);
		
		// 3.
		MypageOrderVO mypageCartVO = new MypageOrderVO();
		
		mypageCartVO.setUserId("doc5_1@doc5.com");
		
		List<MypageOrderVO> list = mypageOrderMapper.doRetrieve(mypageCartVO);
		for(MypageOrderVO vo  :list) {
			log.debug(vo);
		}
		assertEquals(list.size(), 4);
		//총건수
		//assertEquals(1002, list.get(0).getTotalCnt());
		log.debug("총 건수:{}",list.get(0).getTotalCnt());
		
	}
	
	//주문 상세
	//@Disabled
	@Test
	void doSelectOne() {
		
		log.debug("┌──────────────────────────┐");
		log.debug("│──doSelectOne()           │");
		log.debug("└──────────────────────────┘");	
		
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체 삭제
		// 2. 4건 등록
		// 3. 아이디 설정
		// 4. 주문 리스트 확인
				
		// 1. 
		mypageOrderMapper.deleteAll();
		int count = mypageOrderMapper.getCount();
		assertEquals(0, count);
				
		mypageCartMapper.deleteAll();
		count = mypageCartMapper.getCount();
		assertEquals(0, count);
				
				
		// 2-1
		int flag = mypageCartMapper.doSave(mypageCartVO01);
		log.debug("flag : {}", flag);
		log.debug("mypageCartVO01 : {}", mypageCartVO01);
				
		flag = mypageCartMapper.doSave(mypageCartVO02);
		log.debug("flag : {}", flag);
		log.debug("mypageCartVO02 : {}", mypageCartVO02);
				
		flag = mypageCartMapper.doSave(mypageCartVO03);
		log.debug("flag : {}", flag);
		log.debug("mypageCartVO03 : {}", mypageCartVO03);
				
		flag = mypageCartMapper.doSave(mypageCartVO04);
		log.debug("flag : {}", flag);
		log.debug("mypageCartVO04 : {}", mypageCartVO04);
				
		// 2-2
		count = mypageCartMapper.getCount();
		assertEquals(4, count);
						
		// 2-3
		flag = mypageOrderMapper.doSave(mypageOrderVO01);
		log.debug("flag : {}", flag);
		log.debug("mypageOrderVO01 : {}", mypageOrderVO01);
				
		flag = mypageOrderMapper.doSave(mypageOrderVO02);
		log.debug("flag : {}", flag);
		log.debug("mypageOrderVO02 : {}", mypageOrderVO02);
				
		flag = mypageOrderMapper.doSave(mypageOrderVO03);
		log.debug("flag : {}", flag);
		log.debug("mypageOrderVO03 : {}", mypageOrderVO03);
				
		flag = mypageOrderMapper.doSave(mypageOrderVO04);
		log.debug("flag : {}", flag);
		log.debug("mypageOrderVO04 : {}", mypageOrderVO04);
				
		// 2-4
		count = mypageOrderMapper.getCount();
		assertEquals(4, count);
		
		// 3.
		MypageCartVO mypageCartVO = new MypageCartVO();
		
		mypageCartVO.setUserId("doc5_1@doc5.com");
		mypageCartVO.setOrderNo("20251229113030333333");
		
		MypageCartVO outVO = mypageCartMapper.doSelectOne(mypageCartVO);
		
		log.debug("outVO : {}",outVO);
		
		
		
	}

	@Disabled
	@Test
	void beans() {
		log.debug("context:"+context);
		assertNotNull(context);
		
		log.debug("MypageOrderMapper : {}",mypageOrderMapper);
		assertNotNull(mypageOrderMapper);
	}

}
