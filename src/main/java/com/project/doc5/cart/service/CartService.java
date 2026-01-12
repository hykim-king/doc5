package com.project.doc5.cart.service;

import java.util.List;

import com.project.doc5.cart.domain.CartVO;
import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.cart.domain.CartOptionVO;

public interface CartService {
	
	/**
	 * 장바구니 저장
	 * @param param
	 * @return
	 */
	int doCartSave(CartVO param);

	/**
	 * 상품 옵션저장
	 * @param param
	 * @return
	 */
	int doCartOptionSave(CartVO param);

	
	/**
	 * 장바구니 상품 리스트
	 * @param param
	 * @return
	 */
	List<MypageCartVO> doCartList(MypageCartVO param);
	
	/**
	 * 장바구니 상품 옵션 리스트
	 * @param param
	 * @return
	 */
	List<CartOptionVO> doCartOptionList(CartOptionVO param);
	
	/**
	 * 장바구니 상품 수량 변경
	 * @param param
	 * @return
	 */
	int doCartUpdate(MypageCartVO param);	
	
	
	/**
	 * 장바구니 상품 삭제
	 * @param param
	 * @return
	 */
	int doCartDelete(MypageCartVO param);

	
	/**
	 * 전체삭제
	 * @return
	 */
	int cartDeleteAll();

		

}
