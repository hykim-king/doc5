package com.project.doc5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doc5.goods.domain.GoodsInfoVO;
import com.project.doc5.goods.domain.GoodsOptionVO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.goods.domain.OptionTypeVO;
import com.project.doc5.mypage.domain.MypageCartGoodsOptionVO;
import com.project.doc5.mypage.domain.MypageCartVO;


@Mapper
public interface GoodsMapper {

	List<GoodsVO> doRetrieve(String cate);
	
	GoodsVO doSelectOne(GoodsVO param);
	
	int doCartSave(MypageCartVO param);
	
	int doCartGoodsOptionSave(MypageCartGoodsOptionVO param);
	
	List<GoodsInfoVO> doGoodsInfoSelect(GoodsInfoVO param);
	
	List<OptionTypeVO> doOptionTypeSelect(OptionTypeVO param);
	
	List<GoodsOptionVO> doGoodsOptionSelect(GoodsOptionVO param);
}