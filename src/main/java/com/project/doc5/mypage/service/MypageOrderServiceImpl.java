package com.project.doc5.mypage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doc5.cmn.DTO;
import com.project.doc5.mapper.MypageOrderMapper;
import com.project.doc5.mypage.domain.MypageCartGoodsOptionVO;
import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.mypage.domain.MypageOrderVO;

@Service
public class MypageOrderServiceImpl implements MypageOrderService {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	MypageOrderMapper mypageOrderMapper;
	
	
	public MypageOrderServiceImpl() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│MpageServiceImpl()        │");
		log.debug("└──────────────────────────┘");
	}

	@Override
	public List<MypageOrderVO> doRetrieve(DTO dto) {
		return mypageOrderMapper.doRetrieve(dto);
	}

	@Override
	public int doUpdate(MypageOrderVO param) {
		return mypageOrderMapper.doUpdate(param);
	}

	@Override
	public int doDelete(MypageOrderVO param) {
		return mypageOrderMapper.doDelete(param);
	}

	@Override
	public MypageOrderVO doSelectOne(MypageOrderVO param) {
		return mypageOrderMapper.doSelectOne(param);
	}

	@Override
	public int doSave(MypageOrderVO param) {
		return mypageOrderMapper.doSave(param);
	}

	@Override
	public List<MypageOrderVO> doDetailOrder(MypageOrderVO param){
		
		List<MypageOrderVO> mypageOrderVO = mypageOrderMapper.doDetailOrder(param);
		log.debug("┌──────────────────────────┐");
		log.debug("│doDetailOrder()        │");
		log.debug("└──────────────────────────┘");
		if(mypageOrderVO != null) {
			for(MypageCartVO cart : mypageOrderVO.get(0).getcList()) {
	        	double totalGoodsTotalPrice = 0;
	        	double totalOptionPrice = 0;
	        	for(MypageCartGoodsOptionVO opt : cart.getMcgList()) {
	        		totalOptionPrice += opt.getOptionPrice();
	        	}
	        	
	        	totalGoodsTotalPrice = (cart.getGoodsPrice() + totalOptionPrice) * cart.getGoodsCnt();
	        	
	        	log.debug("{}, 상품가격 : {} ,  옵션가격 : {}, 총가격 : {}", cart.getGoodsName() ,cart.getGoodsPrice() ,totalOptionPrice, totalGoodsTotalPrice);
	            cart.setTotalGoodsTotalPrice(totalGoodsTotalPrice);
	        }
		}
		return mypageOrderVO;
	}
}
