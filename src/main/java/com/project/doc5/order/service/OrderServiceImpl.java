package com.project.doc5.order.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doc5.mapper.OrderMapper;
import com.project.doc5.mypage.domain.MypageCartGoodsOptionVO;
import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.order.domain.OrderVO;

@Service
public class OrderServiceImpl implements OrderService {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	OrderMapper orderMapper;
	
	
	
	
	
	public OrderServiceImpl() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│OrderServiceImpl()        │");
		log.debug("└──────────────────────────┘");
	}
	
	@Override
    public int updateCartOrderNo(int seq, String orderNo) {
        return orderMapper.updateCartOrderNo(seq, orderNo);
    }
	
	
	@Override
	public int doOrder(OrderVO param) {
		return orderMapper.doOrder(param);
	}

	@Override
	public List<String> getValidCartSeq(String userId, String[] seq) {
	    return orderMapper.selectExistCartSeq(userId, seq);
	}
	
	
	@Override
	public List<MypageCartVO> doList(List<Long> seqs, String orderType) {

	    log.debug("OrderService.doList 호출");
	    log.debug("seqs = {}", (Object) seqs);
	    log.debug("orderType = {}", orderType);

	    // 1. DB 조회
	    List<MypageCartVO> list = orderMapper.doList(seqs, orderType);

	    // 2. 조회 결과 로그
	    if (list == null) {
	        log.debug("조회 결과: null");
	    } else {
	        log.debug("조회 결과 : {}", list);
	        for (MypageCartVO vo : list) {
	            log.debug("cartSeq={}, goodsName={}",
	                    vo.getSeq(),
	                    vo.getGoodsName());
	        }
	    }
	    for (MypageCartVO vo : list) {
	        double total = vo.getGoodsPrice();

	        

	        if (vo.getMcgList() != null && !vo.getMcgList().isEmpty()) {
	            for (MypageCartGoodsOptionVO opt : vo.getMcgList()) {
	                total += opt.getOptionPrice();
	            }
	        }
	        
	        
	        total = total * vo.getGoodsCnt();
	        vo.setTotalGoodsTotalPrice(total);
	    }
	    // 3. 반드시 조회 결과 반환 ⭐⭐⭐
	    return list;
	}







}
