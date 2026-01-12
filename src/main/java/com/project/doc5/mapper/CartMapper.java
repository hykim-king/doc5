package com.project.doc5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doc5.cart.domain.CartOptionVO;
import com.project.doc5.cart.domain.CartVO;
import com.project.doc5.mypage.domain.MypageCartVO;

@Mapper
public interface CartMapper {
	
	//장바구니 저장
	int doCartSave();
	
	//장바구니 옵션 저장
	int doCartOptionSave();
	
	
	//장바구니 수량 변경
	int doCartUpdate(MypageCartVO param);
	
	//장바구니 단건 삭제
	int doCartDelete(MypageCartVO param);
	
	//장바구니 전체 삭제
	int cartDeleteAll();

	//장바구니 목록 조회
	List<MypageCartVO> doCartList(MypageCartVO param);
	
	//장바구니 목록 조회
	List<CartOptionVO> doCartOptionList(CartOptionVO parem);	


}
