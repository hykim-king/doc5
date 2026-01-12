package com.project.doc5.goods.service;

import java.util.List;

import com.project.doc5.goods.domain.GoodsSearchKeywordVO;
import com.project.doc5.goods.domain.GoodsVO;

public interface GoodsSearchService {

	/**
	 * 키워드 상품 검색
	 * @param keyword
	 * @return
	 */
	List<GoodsVO> goodsSearch(String keyword);
	
	
	/**
	 * 검색 키워드 리스트 
	 * @param param
	 * @return
	 */
	List<GoodsSearchKeywordVO> goodsSearchKeywordList(GoodsSearchKeywordVO param);
	
	/**
	 * 검색 키워드 저장 
	 * @param param
	 * @return
	 */
	int goodsSearchSave(GoodsSearchKeywordVO param);
	
	/**
	 * 비회원 검색 내역을 로그인&회원가입시 검색 이력 승계  
	 * @param param
	 * @return
	 */
	int goodsSearchUpdate(GoodsSearchKeywordVO param);
	
	/**
	 * 이전 이력중 중복 키워드가있을시 중복 키워드 히든처리 
	 * @param param
	 * @return
	 */
	int goodsSearchHiddenUpdate(GoodsSearchKeywordVO param);


	List<GoodsVO> goodsSearchKeywordUp(String keyword, String userId);
}
