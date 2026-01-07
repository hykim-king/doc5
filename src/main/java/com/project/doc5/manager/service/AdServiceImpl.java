package com.project.doc5.manager.service;

import com.project.doc5.mapper.AdMapper;
import com.project.doc5.user.domain.UserVO;
import com.project.doc5.manager.domain.AdOrderVO;
import com.project.doc5.manager.domain.AdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Override
    public AdOrderVO getSalesAndOrderCount(String branchCode) {
        return adMapper.selectSalesAndOrderCount(branchCode);
    }

    @Override
    public List<UserVO> getMemberList(AdDTO dto) {
        return adMapper.selectMemberList(dto);
    }

    @Override
    @Transactional
    public void deleteMemberWithCart(String userId) {
        adMapper.deleteMemberCartData(userId);
        adMapper.deleteMember(userId);
    }

    @Override
    public List<AdOrderVO> getOrdersByMemberId(String userId) {
        return adMapper.selectOrdersByMemberId(userId);
    }

    @Override
    public List<AdOrderVO> getPendingOrders(AdDTO dto) {
        return adMapper.selectPendingOrders(dto);
    }

    @Override
    @Transactional
    public int processOrder(String orderNo, String branchCode, String step) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderNo", orderNo);
        paramMap.put("branchCode", branchCode);

        if ("S".equalsIgnoreCase(step)) {
            return adMapper.updateOrderToSuccess(paramMap);
        } else if ("C".equalsIgnoreCase(step)) {
            return adMapper.updateOrderToCancel(paramMap);
        }
        return 0;
    }
}