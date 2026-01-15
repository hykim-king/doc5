package com.project.doc5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.order.domain.OrderVO;

@Mapper
public interface OrderMapper {
	
	/**
     * 주문서 작성을 위한 상품 및 옵션 상세 리스트 조회
     * @param seqs 주문할 장바구니 일련번호 배열 (IN 절 활용)
     * @param orderType 주문 구분 (direct: 바로구매, 그 외: 장바구니)
     */
    List<MypageCartVO> doList(@Param("seqs")List<Long> seqs, @Param("orderType") String orderType);
    List<String> selectExistCartSeq(@Param("userId") String userId,@Param("seqs") String[] seqs);
    int doOrder(OrderVO param);
    int updateCartOrderNo(@Param("seq") int seq, @Param("orderNo") String orderNo);
    
    
}
