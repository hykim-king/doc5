package com.project.doc5.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals; 

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

import com.project.doc5.branch.domain.BranchVO;
import com.project.doc5.manager.domain.AdDTO;
import com.project.doc5.manager.domain.AdOrderVO;
import com.project.doc5.mapper.AdMapper;
import com.project.doc5.user.domain.Grade;
import com.project.doc5.user.domain.UserVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class AdDaoTest {
    final Logger log = LogManager.getLogger(getClass());

    @Autowired
    AdMapper adMapper;

    private final String TEST_USER_ID = "doc5_1@doc5.com"; 
    private final String TEST_BRANCH_CODE = "s0001";     
    
    // 미처리(P) 상태였던 주문 번호. 테스트 후 S 상태로 변경됩니다.
    private final String TEST_ORDER_NO_P = "20251229093030836592"; 
    // 취소 테스트를 위해 사용할 별도의 미처리 주문 번호 
    private final String TEST_ORDER_NO_P_FOR_CANCEL = "20251229093030836592"; 


    @BeforeEach
    void setUp() throws Exception {
        log.debug("┌──────────────────────────┐");
        log.debug("│──setUp                   │");
        log.debug("└──────────────────────────┘");

    }

    @AfterEach
    void tearDown() throws Exception {
        log.debug("┌──────────────────────────┐");
        log.debug("│──tearDown:               │");
        log.debug("└──────────────────────────┘");
    }

    // ==================== 지점명 조회 (getBranchName) ====================
    @Disabled // 완료
    @Test
    void getBranchName() {
        log.debug("--- getBranchName Test ---");
        BranchVO branchVO = adMapper.getBranchName(TEST_BRANCH_CODE);
        assertNotNull(branchVO, "DB에 지점 코드 " + TEST_BRANCH_CODE + "가 존재해야 합니다.");
        assertNotEquals(null, branchVO.getBranchName(), "지점 이름은 null이 아니어야 합니다.");
        log.debug("조회된 지점명: {}", branchVO.getBranchName());
    }

    // ==================== 월 매출 + 주문 수 (selectSalesAndOrderCount) ====================
    @Disabled //완료
    @Test
    void selectSalesAndOrderCount() {
        log.debug("--- selectSalesAndOrderCount Test ---");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("branchCode", TEST_BRANCH_CODE);

        AdOrderVO resultVO = adMapper.selectSalesAndOrderCount(paramMap);

        assertNotNull(resultVO, "쿼리 결과 VO는 null이 아니어야 합니다.");
        assertTrue(resultVO.getTotalSales() >= 0, "총 매출은 0 이상이어야 합니다.");
        assertTrue(resultVO.getTotalOrderCount() >= 0, "총 주문 수는 0 이상이어야 합니다.");
        log.debug("총 매출: {}, 총 주문 수: {}", resultVO.getTotalSales(), resultVO.getTotalOrderCount());
    }

    // ==================== 회원 목록 조회 (selectMemberList) ====================
    @Disabled 
    @Test
    void selectMemberList() {
        log.debug("--- selectMemberList Test (전체 조회) ---");
        AdDTO dto = new AdDTO(); 

        List<UserVO> allMembers = adMapper.selectMemberList(dto);
        assertNotNull(allMembers, "회원 목록은 null이 아니어야 합니다.");
        assertTrue(allMembers.size() > 0, "DB에 최소 1명의 회원이 존재해야 합니다.");

        log.debug("--- selectMemberList Test (검색어 조회) ---");
        dto.setSearchWord(TEST_USER_ID.substring(0, 4)); // DB에 존재하는 아이디 일부로 검색
        List<UserVO> searchedMembers = adMapper.selectMemberList(dto);
        assertNotNull(searchedMembers, "검색 결과는 null이 아니어야 합니다.");
        assertTrue(searchedMembers.size() >= 1, "검색 결과에 " + TEST_USER_ID + "가 포함되어야 합니다.");
        log.debug("검색된 회원 수: {}", searchedMembers.size());
    }

    // ==================== 회원 삭제 (deleteMemberCartData, deleteMember) ====================
    @Disabled
    @Test
    void deleteMember() {
        log.debug("--- deleteMember Test ---");
        
        // 주의: 이 테스트를 통과시키려면, DB에 'delete_target'과 관련된 카트 데이터가 없거나
        // 테스트 실행 전에 수동으로 등록/삭제 로직을 구현해야 합니다.
        String tempUserId = "delete_target_nonexist";
        
        // 1. 카트 데이터 삭제 (삭제할 데이터가 없으면 0이 반환됨)
        int cartDeleteCount = adMapper.deleteMemberCartData(tempUserId);
        log.debug("삭제된 카트 데이터 수: {}", cartDeleteCount);

        // 2. 회원 삭제 (삭제할 데이터가 없으면 0이 반환됨)
        int memberDeleteCount = adMapper.deleteMember(tempUserId);
        // DB에 데이터가 없으므로 0을 기대합니다. 만약 성공적인 삭제를 원하면 1로 변경해야 합니다.
        assertEquals(0, memberDeleteCount, "DB에 존재하지 않는 ID를 삭제했으므로 0이 반환되어야 합니다."); 
        log.debug("삭제된 회원 수: {}", memberDeleteCount);
    }
    
    // ==================== 지점별 주문 목록 (selectOrdersByBranch) ====================
    //@Disabled //완료
    @Test
    void selectOrdersByBranch() {
        log.debug("--- selectOrdersByBranch Test ---");
        
        List<AdOrderVO> orders = adMapper.getOrdersByBranch(TEST_BRANCH_CODE);
        
        assertNotNull(orders, "주문 목록은 null이 아니어야 합니다.");
        assertTrue(orders.size() >= 1, "DB에 지점 " + TEST_BRANCH_CODE + "의 주문이 최소 1개 존재해야 합니다.");
        log.debug("조회된 지점별 주문 수: {}", orders.size());
        log.debug("=== 지점별 전체 주문 내역 ===");
        for (AdOrderVO order : orders) {
            log.debug("OrderNo: {}, Step: {}, g_Name: {}, OrderDate: {}, tumbler: {}, hot: {}, ice:{}, option_price:{}",
                      order.getOrderNo(),
                      order.getOrderStep(), // 주문 상태 (order_status)를 나타내는 필드라고 가정
                      order.getGoodsName(),
                      order.getTumblerFl(),
                      order.getHotFl(),
                      order.getIceFl(),
                      order.getRegDt(),
                      order.getOptionTotalPrice()
            		);  // 주문 일시를 나타내는 필드라고 가정
            
        }
    }
    
    // ==================== 지점별 미처리 주문 (selectPendingOrders) ====================
    //@Disabled //완료
    @Test
    void selectPendingOrders() {
        log.debug("--- selectPendingOrders Test ---");
        
        AdDTO dto = new AdDTO();
        dto.setBranchCode(TEST_BRANCH_CODE);
        
        List<AdOrderVO> pendingOrders = adMapper.getPendingOrders(dto);
        
        assertNotNull(pendingOrders, "미처리 주문 목록은 null이 아니어야 합니다.");
        
        // TEST_ORDER_NO_P가 DB에 'P' 상태로 존재하는지 확인
        boolean foundPending = pendingOrders.stream()
            .anyMatch(o -> TEST_ORDER_NO_P.equals(o.getOrderNo()) && "P".equals(o.getOrderStep()));
        
        log.debug("조회된 미처리 주문 수: {}", pendingOrders.size());
        
        log.debug("=== (지점) 미처리 주문 내역 ===");
        for (AdOrderVO order : pendingOrders) {
            log.debug("OrderNo: {}, Status: {}, g_Name: {}, OrderDate: {}, tumbler: {}, hot: {}, ice:{},option_price:{}",
                    order.getOrderNo(),
                    order.getOrderStep(), // 주문 상태 (order_status)를 나타내는 필드라고 가정
                    order.getGoodsName(),
                    order.getTumblerFl(),
                    order.getHotFl(),
                    order.getIceFl(),
                    order.getRegDt(),
                    order.getOptionTotalPrice()
                    ); 
        }
    }
    
    // ==================== 주문 상태 변경 (updateOrderToSuccess/Cancel) ====================
    @Disabled // 완료
    @Test
    void updateOrderStep() {
        log.debug("--- updateOrderToSuccess Test ---");
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderNo", TEST_ORDER_NO_P);
        paramMap.put("branchCode", TEST_BRANCH_CODE);

//        // 1. 주문 상태를 'S'(성공)으로 변경
//        // 이 쿼리가 성공하려면 DB에서 TEST_ORDER_NO_P가 반드시 'P' 상태여야 합니다.
//        int successCount = adMapper.updateOrderToSuccess(paramMap);
//        assertEquals(1, successCount, "주문번호 " + TEST_ORDER_NO_P + "는 'P' 상태여서 'S'로 변경 성공해야 합니다.");
        
//        // 2. 주문 상태를 'C'(취소)로 변경 (이미 'S'이므로 변경되면 안 됨)
//        int cancelCount = adMapper.updateOrderToCancel(paramMap);
//        assertEquals(1, cancelCount, "이미 'S' 상태이므로 'C'로 변경되지 않아야 합니다.");
//        
//        log.debug("--- updateOrderToCancel Test ---");
//        
//        // 3. 별도의 주문(TEST_ORDER_NO_P_FOR_CANCEL)의 상태를 'C'(취소)로 변경
//        paramMap.put("orderNo", TEST_ORDER_NO_P_FOR_CANCEL);
//        
//        // 이 쿼리가 성공하려면 DB에서 TEST_ORDER_NO_P_FOR_CANCEL이 반드시 'P' 상태여야 합니다.
//        int newCancelCount = adMapper.updateOrderToCancel(paramMap);
//        assertEquals(1, newCancelCount, "주문번호 " + TEST_ORDER_NO_P_FOR_CANCEL + "는 'P' 상태여서 'C'로 변경 성공해야 합니다.");
    }

}