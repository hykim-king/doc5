package com.project.doc5.order.service;

import java.util.List;

import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.order.domain.OrderVO;

public interface OrderService {

	/**
	 * 카테고리별 상품 조회 
	 * @param param
	 * @return
	 */
	List<MypageCartVO> doList(List<Long> seqs, String orderType);
	
	
	List<String> getValidCartSeq(String userId, String[] seq);

	int updateCartOrderNo(int seq, String orderNo);

	int doOrder(OrderVO param);
}
