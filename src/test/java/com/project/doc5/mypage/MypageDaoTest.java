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
		mypageOrderVO01 = new MypageOrderVO("20251227093030123456","s0001","아메리카노",1,1700.00,1700.00,
				0.00,"BA","C","2025-12-27 09:30:30","2025-12-27 09:30:50");
	
		mypageOrderVO02 = new MypageOrderVO("20251228123030654321","s0001","아메리카노",1,2000.00,2000.00,
				0.00,"BA","S","2025-12-28 12:30:30","2025-12-28 12:30:50");
		
		mypageOrderVO03 = new MypageOrderVO("20251229093030836592","s0001","아메리카노",1,2000.00,2000.00,
				0.00,"BA","P","2025-12-29 09:30:30","2025-12-29 09:30:50");
		
		mypageOrderVO04 = new MypageOrderVO("20251229113030333333","s0001","아메리카노",1,2000.00,2000.00,
				0.00,"BA","O","2025-12-29 11:30:30","2025-12-29 11:30:50");
		
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
		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──tearDown────────────────│");
		log.debug("└──────────────────────────┘");	
	}
	
	//@Disabled
	@Test
	void doSave() {
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체 삭제
		// 2. 단건등록board01)	
		// 3. 전체건수 조회
		// 4. 실제데이터 조회
				
		// 1. 
		mypageOrderMapper.deleteAll();
		int count = mypageOrderMapper.getCount();
		assertEquals(0, count);
		
		mypageCartMapper.deleteAll();
		count = mypageCartMapper.getCount();
		assertEquals(0, count);
		
		
		// 2.
		int flag = mypageOrderMapper.doSave(mypageOrderVO01);
		log.debug("flag : {}", flag);
		log.debug("mypageOrderVO01 : {}", mypageOrderVO01);
		
		count = mypageOrderMapper.getCount();
		assertEquals(1, count);
		
		flag = mypageCartMapper.doSave(mypageCartVO01);
		log.debug("flag : {}", flag);
		log.debug("mypageCartVO01 : {}", mypageCartVO01);
		
		
		MypageOrderVO mypageOrderOutVO = mypageOrderMapper.doSelectOne(mypageOrderVO01);
		log.debug("mypageOrderOutVO : {}",mypageOrderOutVO);
		assertNotNull(mypageOrderOutVO);
		
		MypageCartVO mypageCartOutVO = mypageCartMapper.doSelectOne(mypageCartVO01);
		log.debug("mypageCartVO01 : {}",mypageCartVO01);
		assertNotNull(mypageCartOutVO);
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
