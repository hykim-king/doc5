package com.project.doc5.mypage.service;

import java.util.List;

import com.project.doc5.cmn.WorkDiv;
import com.project.doc5.mypage.domain.MypageOrderVO;

public interface MypageOrderService extends WorkDiv<MypageOrderVO> {

	List<MypageOrderVO> doDetailOrder(MypageOrderVO param);
	
}
