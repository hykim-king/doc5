package com.project.doc5.order.service;

import java.util.List;

import com.project.doc5.mypage.domain.MypageCartVO;

public interface OrderService {

	/**
	 * 카테고리별 상품 조회 
	 * @param param
	 * @return
	 */
	List<MypageCartVO> doList(String seq, String orderType);
	
	int doUpdate(MypageCartVO param);
	
}
