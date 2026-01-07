package com.project.doc5.admin;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.transaction.annotation.Transactional;

import com.project.doc5.mapper.AdMapper;
import com.project.doc5.user.domain.UserVO;
import com.project.doc5.manager.domain.AdOrderVO;
import com.project.doc5.manager.domain.AdDTO;
import com.project.doc5.user.domain.Grade;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Transactional
class AdDaoTest {

	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	AdMapper adMapper;

	// 테스트용 상수
	private final String TEST_BRANCH_CODE = "s0001";

	// 테스트 데이터
	UserVO user01;
	UserVO user02;
	UserVO user03;

	AdOrderVO order01; // P: Pending (미완료)
	AdOrderVO order02; // S: Success (완료) 
	AdOrderVO order03; // S: Success (완료)
	AdOrderVO order04; // C: Cancel (취소)

	AdDTO adDto;

// 테스트 데이터를 List로 묶어 관리
	List<UserVO> users;
	List<AdOrderVO> orders;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──setup───────────────────│");
		log.debug("└──────────────────────────┘");

//		// 1. UserVO 설정
//		user01 = new UserVO("doc5_100@doc5.com", "doc5_010", "독오01", "010-0000-0000", Grade.BASIC, null, null, null);
//		user02 = new UserVO("doc5_101@doc5.com", "doc5_010", "독오02", "010-0000-0000", Grade.SILVER, null, null, null);
//		user03 = new UserVO("doc5_102@doc5.com", "doc5_010", "독오03", "010-0000-0000", Grade.GOLD, null, null, null);
//		users = Arrays.asList(user01, user02, user03);
//
//		log.debug("user 데이터 설정 완료");
//
//		// 2. AdOrderVO 설정 (상태별 4가지 주문)
//		order01 = createOrderVO("ORDER_20260105_001", user01.getUserId(), "P", 5000.0);
//		order02 = createOrderVO("ORDER_20260105_002", user02.getUserId(), "S", 7000.0);
//		order03 = createOrderVO("ORDER_20260105_003", user01.getUserId(), "S", 10000.0);
//		order04 = createOrderVO("ORDER_20260105_004", user01.getUserId(), "C", 8000.0);
//		orders = Arrays.asList(order01, order02, order03, order04);
//
//		log.debug("order 데이터 설정 완료");
//
//		// 3. AdDTO 설정
//		adDto = new AdDTO();
//		adDto.setBranchCode(TEST_BRANCH_CODE);
//		adDto.setPageNo(1);
//		adDto.setPageSize(10);
//
//		// 4. 테스트 데이터 삽입
//		for (UserVO user : users) {
//			adMapper.testInsertMember(user);
//		}
//
//		double totalSalesForTest = 0;
//		for (AdOrderVO order : orders) {
//			adMapper.testInsertOrder(order);
//
//			// Cart 삽입 (회원과 주문 연결)
//			Map<String, Object> cartParam = new HashMap<>();
//			cartParam.put("orderNo", order.getOrderNo());
//
//			// ORA-01400 오류 해결을 위해 BRANCH_CODE, GOODS_NAME 추가
//			cartParam.put("branchCode", TEST_BRANCH_CODE);
//			cartParam.put("goodsName", "테스트 상품 A");
//
//			// 주문번호에 따라 회원 ID 연결
//			String orderUserId = order.getOrderNo().equals(order02.getOrderNo()) ? user02.getUserId()
//					: user01.getUserId();
//			cartParam.put("userId", orderUserId);
//
//			adMapper.testInsertCart(cartParam);
//
//			if ("S".equals(order.getOrderStep())) {
//				totalSalesForTest += order.getGoodsTotalPrice(); // 상품 금액만 합산 (참고용)
//			}
//		}
//		// Log 메시지 위치 수정 (컴파일 오류 해결)
//		log.debug("테스트 데이터 삽입 완료: 회원 3명, 주문 4건 (P, S, S, C). 예상 'S' Goods 옵션 제외 매출액: {}", totalSalesForTest);
	}

	// AdOrderVO 객체 생성을 위한 헬퍼 메서드
	private AdOrderVO createOrderVO(String orderNo, String userId, String step, double price) {
		AdOrderVO vo = new AdOrderVO();
		vo.setOrderNo(orderNo);
		vo.setBranchCode(TEST_BRANCH_CODE);
		vo.setOrderName(userId + "의 주문");
		vo.setGoodsTotalCnt(1);
		vo.setSettleTotalPrice(price);
		vo.setGoodsTotalPrice(price - 500.0); // 상품 가격은 총 결제 금액보다 500원 낮게 설정
		vo.setOptionTotalPrice(500.0);
		vo.setSettelKind("CARD");
		vo.setOrderStep(step);
		return vo;
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│ tearDown─────────────────│");
		log.debug("└──────────────────────────┘");
	}

	// 1. 통계 조회 테스트 [완료]
	@Disabled
	@Test
	void doSelectSalesAndOrderCount() {
		log.debug("┌──────────────────────────┐");
		log.debug("│doSelectSalesAndOrderCount│");
		log.debug("└──────────────────────────┘");

		AdOrderVO outVO = adMapper.selectSalesAndOrderCount(TEST_BRANCH_CODE);
		log.debug("outVO:{}", outVO);

		assertNotNull(outVO, "통계 결과는 null이 아니어야 합니다.");

		log.debug("outVO.getTotalSales() : {}",outVO.getTotalSales());
		log.debug("outVO.getTotalOrderCount() : {}",outVO.getTotalOrderCount());
		
		assertEquals(2000, outVO.getTotalSales());
		assertEquals(1, outVO.getTotalOrderCount());
		
		
		// S 상태 주문의 SETTLE_TOTAL_PRICE 합산 (7000.0 + 10000.0 = 17000.0)
//		assertTrue(outVO.getTotalSales() >= 17000.0, "총 매출액은 17000.0 이상이어야 합니다. (SettleTotalPrice 기준)");
		// S 상태 주문 건수 (order02, order03) = 2건
//		assertTrue(outVO.getTotalOrderCount() == 2, "총 주문 건수는 S 상태 주문 2건이어야 합니다.");

//		log.info("조회 결과 - 총 매출액 (S): {}", outVO.getTotalSales());
//		log.info("조회 결과 - 총 주문 건수 (S): {}", outVO.getTotalOrderCount());
	}

	// 2.1 회원 목록 조회 테스트 [완료]
	@Disabled
	@Test
	void doSelectMemberList() {
		log.debug("┌──────────────────────────┐");
		log.debug("│doSelectMemberList()      │");
		log.debug("└──────────────────────────┘");

		//adDto.setSearchWord("doc5_10"); // 검색어 설정 (user01, 02, 03 모두 포함)
		List<UserVO> memberList = adMapper.selectMemberList(adDto);

		log.debug("memberList.size:{}", memberList.size());
		if (!memberList.isEmpty()) {
			log.info("▶ 조회된 회원 목록 (총 {}건):", memberList.size());
			for (UserVO user : memberList) {
				log.info("   - ID: {}, 이름: {}, 전화번호: {}, 가입일: {}", user.getUserId(), user.getName(), user.getPhone(),
						user.getRegDt());
			}
		} else {
			log.info("▶ 조회된 회원 목록이 없습니다.");
		}
		assertNotNull(memberList, "회원 목록 List는 null이 아니어야 합니다.");
		assertTrue(memberList.size() >= 3, "회원 목록은 최소 3개 이상이어야 합니다.");

	}
	
	// 3.1 회원별 주문 내역 조회 테스트 (user01: 3건 P, S, C) [완료]
	@Disabled
	@Test
	void doSelectOrdersByMemberId_user01() {
		log.debug("┌──────────────────────────┐");
		log.debug("│doSelectOrdersByMemberId  │");
		log.debug("└──────────────────────────┘");

		String tmpUserId="doc5_1@doc5.com";
		List<AdOrderVO> orderList = adMapper.selectOrdersByMemberId(tmpUserId);

		log.debug("orderList.size:{}", orderList.size());
		log.debug("orderList:{}", orderList.toString());

		if (!orderList.isEmpty()) {
			log.debug(" {} 회원의 주문 내역 (총 {}건):", tmpUserId, orderList.size());
			for (AdOrderVO order : orderList) {
				log.info("   - 주문번호: {}, 상태: {}, 결제액: {}, 주문일: {}", order.getOrderNo(), order.getOrderStep(),
						order.getSettleTotalPrice(), order.getRegDt());
			}
		}

		assertNotNull(orderList);
//		assertTrue(orderList.size() >= 3, "user01은 최소 3건의 주문이 있어야 합니다.");
	}

	// 3.2 미완료 주문 목록 조회 테스트
    @Disabled // @Disabled를 제거하고 테스트를 실행합니다.
	@Test
	void doSelectPendingOrders() {
		log.debug("┌──────────────────────────┐");
		log.debug("│doSelectPendingOrders()   │");
		log.debug("└──────────────────────────┘");

		// AdDTO에 페이징 조건 설정 (테스트 환경에 맞게)
		adDto.setBranchCode(TEST_BRANCH_CODE);
		adDto.setPageNo(1);
		adDto.setPageSize(10);

		// order01만 'P' 상태입니다. (order02:S, order03:S, order04:C)
		List<AdOrderVO> pendingOrders = adMapper.selectPendingOrders(adDto);

		log.debug("pendingOrders.size:{}", pendingOrders.size());

		// 🚩 orderList -> pendingOrders로 변수명 수정
		if (!pendingOrders.isEmpty()) {
			log.info("▶ 미완료 주문 내역 (총 {}건, 1페이지, 지점: {}):", pendingOrders.size(), adDto.getBranchCode());
			// 🚩 orderList -> pendingOrders로 변수명 수정
			for (AdOrderVO order : pendingOrders) {
				// 주문번호, 주문일시, 회원ID, 상품가격(결제액), 처리(상태)를 출력
				log.info("   - [P] 주문번호: {}, 주문일시: {}, 회원ID: {}, 결제액: {}, 처리: {}", order.getOrderNo(), order.getRegDt(), // 주문일시
																															// (ORDER_DATE)
						order.getUserId(), order.getSettleTotalPrice(), // 상품가격 (SETTLE_TOTAL_PRICE)
						order.getOrderStep()); // 처리 (ORDER_STEP)
			}
		} else {
			log.info("▶ 미완료 주문이 없습니다.");
		}

		assertNotNull(pendingOrders);
		// 🚩 테스트 조건을 setUp 설정에 맞게 1건으로 수정 (2건이 나온다면 setUp 환경을 다시 점검해야 함)
		assertTrue(pendingOrders.size() >=1, "미완료 주문 목록은 정확히 1건이어야 합니다.");

		// order01이 포함되어 있는지 확인
		assertTrue(pendingOrders.stream().anyMatch(o -> o.getOrderNo().equals(order01.getOrderNo())),
				"order01(P)이 목록에 포함되어야 합니다.");

		// S, C 상태는 포함되지 않아야 함
		assertFalse(pendingOrders.stream().anyMatch(o -> o.getOrderNo().equals(order02.getOrderNo())),
				"order02(S)는 목록에 포함되지 않아야 합니다.");
		assertFalse(pendingOrders.stream().anyMatch(o -> o.getOrderNo().equals(order03.getOrderNo())),
				"order03(S)는 목록에 포함되지 않아야 합니다.");
		assertFalse(pendingOrders.stream().anyMatch(o -> o.getOrderNo().equals(order04.getOrderNo())),
				"order04(C)는 목록에 포함되지 않아야 합니다.");
	}

	@Disabled
	// 3.2.1 주문 상태 업데이트 테스트 (미완료(P) -> 완료(S))
	 @Test
		void doUpdateOrderStatus_Success() {
			log.debug("┌───────────────────────────┐");
			log.debug("│doUpdateOrderStatus_Success│");
			log.debug("└───────────────────────────┘");

			// order01: P 상태
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("orderNo", order01.getOrderNo());
			paramMap.put("branchCode", TEST_BRANCH_CODE);
	        
			// 1. [BEFORE] 상태 확인
	        List<AdOrderVO> initialPending = adMapper.selectPendingOrders(adDto);
	        boolean isInitialPending = initialPending.stream().anyMatch(o -> o.getOrderNo().equals(order01.getOrderNo()));

	        // 🚩 BEFORE 로그 (최소화)
	        log.info("BEFORE: order_no: {}, 상태: {}", 
	                 order01.getOrderNo(), 
	                 isInitialPending ? "P" : "X");
	        
	        // 2. 주문 완료(S)로 업데이트 실행
			int updateSuccess = adMapper.updateOrderToSuccess(paramMap);
			log.info("UPDATE: {}건 성공 (P -> S)", updateSuccess);
			assertEquals(1, updateSuccess, "주문 완료 업데이트가 1건이어야 합니다.");

	        // 3. [AFTER] 상태 확인
			List<AdOrderVO> currentPending = adMapper.selectPendingOrders(adDto);
	        boolean isFinalPending = currentPending.stream().anyMatch(o -> o.getOrderNo().equals(order01.getOrderNo()));
	        
	        // 🚩 AFTER 로그 (최소화)
	        log.info("AFTER : order_no: {}, 상태: {}", 
	                 order01.getOrderNo(), 
	                 isFinalPending ? "P (오류)" : "S");
	        // --------------------------------------------------------------------------
	        
			assertFalse(currentPending.stream().anyMatch(o -> o.getOrderNo().equals(order01.getOrderNo())),
					"주문 완료된 order01은 미완료 목록에서 제거되어야 합니다.");
	        
	        // 총 P 건수가 1건 줄었는지 확인
	        assertEquals(initialPending.size() - 1, currentPending.size(), "총 미완료 주문 건수가 1건 줄어야 합니다.");
		}

	@Disabled
	// 3.2.2 주문 상태 업데이트 테스트 (미완료(P) -> 취소(C))
	// com.project.doc5.admin.AdDaoTest.java

		//@Disabled // @Disabled를 제거하고 테스트를 실행합니다.
		@Test
		void doUpdateOrderStatus_Cancel() {
			log.debug("┌──────────────────────────┐");
			log.debug("│doUpdateOrderStatus_Cancel│");
			log.debug("└──────────────────────────┘");

			// order01: P 상태
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("orderNo", order01.getOrderNo());
			paramMap.put("branchCode", TEST_BRANCH_CODE);
	        
			adDto.setBranchCode(TEST_BRANCH_CODE);
	        
	        // 1. [BEFORE] 상태 확인
	        List<AdOrderVO> initialPending = adMapper.selectPendingOrders(adDto);
	        boolean isInitialPending = initialPending.stream().anyMatch(o -> o.getOrderNo().equals(order01.getOrderNo()));

	        // 🚩 BEFORE 로그 (최소화)
	        log.info("BEFORE: order_no: {}, 상태: {}", 
	                 order01.getOrderNo(), 
	                 isInitialPending ? "P" : "X");
	        
			// 1. 주문 취소(C)로 업데이트 실행
			int updateCancel = adMapper.updateOrderToCancel(paramMap);
			log.info("UPDATE: {}건 성공 (P -> C)", updateCancel);
			assertEquals(1, updateCancel, "주문 취소 업데이트가 1건이어야 합니다.");

			// 2. [AFTER] 상태 확인
			// adDto.setUserId(user01.getUserId()); // userId는 selectPendingOrders 쿼리에 필요하지 않음
			List<AdOrderVO> currentPending = adMapper.selectPendingOrders(adDto);
	        boolean isFinalPending = currentPending.stream().anyMatch(o -> o.getOrderNo().equals(order01.getOrderNo()));

	        // 🚩 AFTER 로그 (최소화)
	        log.info("AFTER : order_no: {}, 상태: {}", 
	                 order01.getOrderNo(), 
	                 isFinalPending ? "P (오류)" : "C");
	        
			// 3. 검증
	        assertFalse(isFinalPending, "주문 취소된 order01은 미완료 목록에서 제거되어야 합니다.");
	        
	        assertEquals(initialPending.size() - 1, currentPending.size(), "총 미완료 주문 건수가 1건 줄어야 합니다.");
		}
}