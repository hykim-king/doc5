package com.project.doc5.manager.service;

import com.project.doc5.user.domain.UserVO;
import com.project.doc5.manager.domain.AdOrderVO;
import com.project.doc5.manager.domain.AdDTO;
import java.util.List;

public interface AdService {
    
    AdOrderVO getSalesAndOrderCount(String branchCode);

    List<UserVO> getMemberList(AdDTO dto);

    void deleteMemberWithCart(String userId);

    List<AdOrderVO> getOrdersByMemberId(String userId);
    List<AdOrderVO> getPendingOrders(AdDTO dto);
    
    int processOrder(String orderNo, String branchCode, String step); 
}