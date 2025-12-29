package com.project.doc5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doc5.goods.domain.GoodsVO;


@Mapper
public interface GoodsMapper {

	GoodsVO SelectGoodsWithOptions(int goodsVO);
	List<GoodsVO> SelectGoodsCategory(String code);
	GoodsVO SelectGoods(int goodsVO);
	GoodsVO SelectOptiontype(int goodsVO);
	GoodsVO SelectGoodsOption(int goodsVO);
	GoodsVO SelectGoodsInfo(int goodsVO);
	GoodsVO SelectCategory(int goodsVO);
}