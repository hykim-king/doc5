package com.project.doc5.mapper;

import com.project.doc5.user.domain.UserVO;
import com.project.doc5.manager.domain.AdOrderVO;
import com.project.doc5.mypage.domain.MypageOrderVO;
import com.project.doc5.branch.domain.BranchVO;
import com.project.doc5.manager.domain.AdDTO;
import java.util.List;
import java.util.Map;


public interface AdMapper {

    // ==================== 지점명 조회 (XML: getBranchName) ====================
    BranchVO getBranchName(String branchCode); // XML ID와 일치하도록 메서드명을 'getBranchName'으로 변경
    // ==================== 월 매출 + 주문 수 (XML: selectSalesAndOrderCount) ====================
    AdOrderVO selectSalesAndOrderCount(Map<String, Object> paramMap);
    // ==================== 회원 목록 조회 (XML: selectMemberList) ====================
    public List<UserVO> selectMemberList(AdDTO dto);
    // ==================== 회원 삭제 (XML: deleteMemberCartData, deleteMember) ====================
    int deleteMemberCartData(String userId);
    int deleteMember(String userId);
    // ==================== 지점별 회원 주문 목록 (XML: selectOrdersByBranch) ====================
    List<AdOrderVO> selectOrdersByBranch(String branchCode);
    // ==================== 지점별 미처리 주문 (XML: selectPendingOrders) ====================
    List<AdOrderVO> selectPendingOrders(AdDTO dto);
    // ==================== 주문 상태 변경 (XML: updateOrderToSuccess/Cancel) ====================
    int updateOrderToSuccess(Map<String, Object> paramMap);
    int updateOrderToCancel(Map<String, Object> paramMap);
    int updateOrderToP(Map<String, Object> paramMap);
    
    
    
    
    // ==================== 테스트용 INSERT (XML: testInsert...) ====================
    int testInsertMember(UserVO vo);
    int testInsertOrder(AdOrderVO vo);
    int testInsertCart(Map<String, Object> paramMap);

    List<MypageOrderVO> doDetailOrder(MypageOrderVO param);

 
}