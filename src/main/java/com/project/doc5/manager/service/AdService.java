package com.project.doc5.manager.service;

import com.project.doc5.user.domain.UserVO;
import com.project.doc5.manager.domain.AdOrderVO;
import com.project.doc5.mypage.domain.MypageOrderVO;
import com.project.doc5.manager.domain.AdDTO;
import com.project.doc5.branch.domain.BranchVO; // BranchVO 추가

import java.nio.channels.InterruptedByTimeoutException;
import java.util.List;

public interface AdService {
    
    // 1. 지점명 조회 (Mapper의 getBranchName에 대응)
    // 서비스 계층에 지점명 조회 기능 추가
    BranchVO getBranchName(String branchCode); 
    
    // 2. 월 매출 + 주문 수 조회 (Mapper의 selectSalesAndOrderCount에 대응)
    // 현재 Mapper는 Map을 받지만, Service는 DTO/VO 또는 단일 String을 받는 것이 일반적.
    // 여기서는 branchCode(String)만 Service에서 받아 내부적으로 Map을 만들도록 유지.
    AdOrderVO getSalesAndOrderCount(String branchCode);

    // 3. 회원 목록 조회
    List<UserVO> getMemberList(String searchWord);

    // 4. 회원 및 카트 데이터 삭제 (트랜잭션 처리 예상)
    void deleteMemberWithCart(String userId);

    List<AdOrderVO> getPendingOrders(AdDTO dto);
    List<AdOrderVO> getOrdersByBranch(AdDTO dto);
    
    // 7. 주문 상태 변경 처리
    // Mapper는 orderNo, branchCode를 Map으로 받지만, Service는 풀어서 받는 것이 사용하기 편리함
    int processOrder(String orderNo, String branchCode, String step);
    
    List<MypageOrderVO> doDetailOrder(MypageOrderVO param);
    
    int getCountPO(String branchCode);
    int getCountAll(String branchCode);
}