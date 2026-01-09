package com.project.doc5.goods.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doc5.cmn.DTO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.mapper.GoodsMapper;

@Service
public class GoodsServiceImpl implements GoodsService {

	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	GoodsMapper goodsMapper;
	
	
	
	public GoodsServiceImpl() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│GoodsServiceImpl          │");
		log.debug("└──────────────────────────┘");	
	}

	
	@Override
	public GoodsVO selectGoodsWithOptions(int goodsNo) {
		
		return goodsMapper.selectGoodsWithOptions(goodsNo);
	}

	@Override
	public List<GoodsVO> selectGoodsCategory(String code) {

		return goodsMapper.selectGoodsCategory(code);
	}

}
