package com.project.doc5.goods.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doc5.goods.domain.GoodsSearchKeywordVO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.mapper.GoodsSearchMapper;

@Service
public class GoodsSearchServiceImpl implements GoodsSearchService {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	GoodsSearchMapper goodsSearchMapper;
	
	@Override
	public List<GoodsVO> goodsSearch(String keyword) {
		return goodsSearchMapper.goodsSearch(keyword);
	}

	@Override
	public List<GoodsSearchKeywordVO> goodsSearchKeywordList(GoodsSearchKeywordVO param) {
		return goodsSearchMapper.goodsSearchKeywordList(param);
	}

	@Override
	public int goodsSearchSave(GoodsSearchKeywordVO param) {
		return goodsSearchMapper.goodsSearchSave(param);
	}

	@Override
	public int goodsSearchUpdate(GoodsSearchKeywordVO param) {
		return goodsSearchMapper.goodsSearchUpdate(param);
	}

	@Override
	public int goodsSearchHiddenUpdate(GoodsSearchKeywordVO param) {
		return goodsSearchMapper.goodsSearchHiddenUpdate(param);
	}
	
	public List<GoodsVO> goodsSearchKeywordUp(String keyword, String userId) {
		
		GoodsSearchKeywordVO gskVO = new GoodsSearchKeywordVO(0, userId, keyword, "N","등록일", null);

		this.goodsSearchHiddenUpdate(gskVO);
		log.debug("gskVO: {}",gskVO);
		//키워드 저장 
		this.goodsSearchSave(gskVO);
		
		return goodsSearchMapper.goodsSearch(keyword);
	}

}
