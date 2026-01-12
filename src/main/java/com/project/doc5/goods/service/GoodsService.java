package com.project.doc5.goods.service;

import java.util.List;

import com.project.doc5.goods.domain.GoodsInfoVO;
import com.project.doc5.goods.domain.GoodsOptionVO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.goods.domain.OptionTypeVO;

public interface GoodsService {

	/**
	 * 카테고리별 상품 조회 
	 * @param param
	 * @return
	 */
	List<GoodsVO> doRetrieve(String cate);
	
	/**
	 * 상품 상세 조회 
	 */
	GoodsVO doSelectOne(GoodsVO param);
	
	
	/**
	 * 상품 카트에 저장 
	 */
	int doCartSave(GoodsVO param);
	
	/**
	 * 상품 상세 정보 조회 
	 * @param param
	 * @return
	 */
	List<GoodsInfoVO> doGoodsInfoSelect(GoodsInfoVO param);
	
	
	/**
	 *상품 옵션 종류 조회 
	 * @param param
	 * @return
	 */
	List<OptionTypeVO> doOptionTypeSelect(OptionTypeVO param);
	
	/**
	 * 상품 선택 옵션 상세 정보 조회 
	 * @param param
	 * @return
	 */
	List<GoodsOptionVO> doGoodsOptionSelect(GoodsOptionVO param);
	
}
