package com.project.doc5.mapper;
import com.project.doc5.user.domain.UserVO;
import com.project.doc5.manager.domain.AdOrderVO;
import com.project.doc5.manager.domain.AdDTO;
import java.util.List;
import java.util.Map;
public interface AdMapper {
    AdOrderVO selectSalesAndOrderCount(String branchCode);
    List<UserVO> selectMemberList(AdDTO dto);
    int deleteMemberCartData(String userId);
    int deleteMember(String userId);
    List<AdOrderVO> selectOrdersByMemberId(String userId);
    List<AdOrderVO> selectPendingOrders(AdDTO dto);
    int updateOrderToSuccess(Map<String, Object> paramMap);
    int updateOrderToCancel(Map<String, Object> paramMap);
 // Test용 메서드 추가
    int testInsertMember(UserVO vo);
    int testInsertOrder(AdOrderVO vo);
    int testInsertCart(Map<String, Object> paramMap);
    // Test: 데이터 삭제 (롤백 역할)
    int testDeleteMember(String userId);
    int testDeleteOrder(String orderNo);
    int testDeleteCart(String orderNo);
}