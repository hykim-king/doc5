package com.project.doc5.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.doc5.branch.domain.BranchVO;
import com.project.doc5.goods.domain.CategoryJoinVO;
import com.project.doc5.goods.domain.GoodsInfoVO;
import com.project.doc5.goods.domain.GoodsOptionVO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.goods.domain.OptionTypeVO;


@Mapper
public interface DataInsertMapper {
	
	int deleteBranchAll();
	
	int deleteGoodsInfoAll();
	
	int deleteGoodsAll();
	
	int deleteGoodsOptionAll();
	
	int doSave(BranchVO param);
	
	int doGoodsSave(GoodsVO param);
	
	int doGoodsOptionSave(GoodsOptionVO param);
	
	int goodsAllCount();
	
	int doGoodsInfoSave(GoodsInfoVO param);
	
	int doGoodsOptionTypeSave(OptionTypeVO param);
	
	int deleteOptionTypeAll();
	
	int deleteCategoryJoinAll();
	
	int doCategoryJoinSave(CategoryJoinVO param);
	
}
