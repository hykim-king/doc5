package com.project.doc5.mapper;

import java.util.List;

import com.project.doc5.goods.domain.GoodsSearchKeywordVO;
import com.project.doc5.goods.domain.GoodsVO;

public interface GoodsSearchMapper {

	List<GoodsVO> goodsSearch(String keyword);
	
	List<GoodsSearchKeywordVO> goodsSearchKeywordList(GoodsSearchKeywordVO param);
	
	int goodsSearchSave(GoodsSearchKeywordVO param);
	
	int goodsSearchUpdate(GoodsSearchKeywordVO param);
	
	int goodsSearchHiddenUpdate(GoodsSearchKeywordVO param);
}
