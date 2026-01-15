package com.project.doc5.manager.service;

import com.project.doc5.mapper.AdMapper;
import com.project.doc5.mypage.domain.MypageCartGoodsOptionVO;
import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.mypage.domain.MypageOrderVO;
import com.project.doc5.user.domain.UserVO;
import com.project.doc5.manager.domain.AdOrderVO;
import com.project.doc5.manager.domain.AdDTO;
import com.project.doc5.branch.domain.BranchVO;
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

    // ==================== 1. 지점명 조회 (신규 추가) ====================
    @Override
    public BranchVO getBranchName(String branchCode) {
        return adMapper.getBranchName(branchCode);
    }
    
    // ==================== 2. 월 매출 + 주문 수 조회 ====================
    @Override
    public AdOrderVO getSalesAndOrderCount(String branchCode) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("branchCode", branchCode);
        
        return adMapper.selectSalesAndOrderCount(paramMap); 
    }


 // 3. 회원 목록 조회 
    @Override
    public List<UserVO> getMemberList(String searchWord) {

        AdDTO dto = new AdDTO();
        dto.setSearchWord(searchWord); 

        return adMapper.selectMemberList(dto);
    }

    // ==================== 4. 회원 및 카트 데이터 삭제 ====================
    @Override
    @Transactional
    public void deleteMemberWithCart(String userId) {
        adMapper.deleteMemberCartData(userId);
        adMapper.deleteMember(userId);
    }

    @Override
    public List<AdOrderVO> getPendingOrders(AdDTO dto) {
        return adMapper.getPendingOrders(dto);
    }

    @Override
    public List<AdOrderVO> getOrdersByBranch(AdDTO dto) {
        return adMapper.getOrdersByBranch(dto);
    }

    // ==================== 7. 주문 상태 변경 처리 ====================
    @Override
    @Transactional
    public int processOrder(String orderNo, String branchCode, String step) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderNo", orderNo);
        paramMap.put("branchCode", branchCode);
        int flag = 0;
        if ("S".equalsIgnoreCase(step)) {
        	flag = adMapper.updateOrderToSuccess(paramMap);
        } else if ("C".equalsIgnoreCase(step)) {
        	flag = adMapper.updateOrderToCancel(paramMap);
        } else if ("P".equalsIgnoreCase(step)) {
        	flag = adMapper.updateOrderToP(paramMap);
        }
        return flag;
    }
    
    @Override
	public List<MypageOrderVO> doDetailOrder(MypageOrderVO param){
		
		List<MypageOrderVO> mypageOrderVO = adMapper.doDetailOrder(param);
		
		if(mypageOrderVO != null) {
			for(MypageCartVO cart : mypageOrderVO.get(0).getcList()) {
	        	double totalGoodsTotalPrice = 0;
	        	double totalOptionPrice = 0;
	        	for(MypageCartGoodsOptionVO opt : cart.getMcgList()) {
	        		totalOptionPrice += opt.getOptionPrice();
	        	}
	        	
	        	totalGoodsTotalPrice = (cart.getGoodsPrice() + totalOptionPrice) * cart.getGoodsCnt();
	        	
	            cart.setTotalGoodsTotalPrice(totalGoodsTotalPrice);
	        }
		}
		return mypageOrderVO;
	}

    @Override
    public int getCountPO(String branchCode) {
        return adMapper.getCountPO(branchCode);
    }

    @Override
    public int getCountAll(String branchCode) {
        return adMapper.getCountAll(branchCode);
    }
}