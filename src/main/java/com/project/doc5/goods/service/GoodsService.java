package com.project.doc5.goods.service;

import java.util.List;

import com.project.doc5.cmn.WorkDiv;
import com.project.doc5.goods.domain.GoodsVO;

public interface GoodsService {

	List<GoodsVO> selectGoodsCategory(String code);
	GoodsVO selectGoodsWithOptions(int goodsNo);
	
}
