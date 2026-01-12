package com.project.doc5.cart.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doc5.cart.domain.CartOptionVO;
import com.project.doc5.cart.domain.CartVO;
import com.project.doc5.mapper.CartMapper;
import com.project.doc5.mypage.domain.MypageCartGoodsOptionVO;
import com.project.doc5.mypage.domain.MypageCartVO;

@Service
public class CartServiceImpl implements CartService {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	CartMapper cartMapper;
	
	public CartServiceImpl() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│CartServiceImpl()         │");
		log.debug("└──────────────────────────┘");	
	}

	@Override
	public int doCartSave(CartVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doCartOptionSave(CartVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MypageCartVO> doCartList(MypageCartVO param) {
		List<MypageCartVO> mypageCartVO = cartMapper.doCartList(param);
		
	    if(mypageCartVO != null) {
	        for(MypageCartVO cart : mypageCartVO) {
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
	    //return (this.goods_price + totalOption) * this.goods_cnt;
	    log.debug("mypageCartVO : {}",mypageCartVO);
		return mypageCartVO;
	}
	
	
	
	
	@Override
	public List<CartOptionVO> doCartOptionList(CartOptionVO param) {
		return cartMapper.doCartOptionList(param);
	}	

	@Override
	public int doCartUpdate(MypageCartVO param) {
		return cartMapper.doCartUpdate(param);
	}

	@Override
	public int doCartDelete(MypageCartVO param) {
		return cartMapper.doCartDelete(param);
	}

	@Override
	public int cartDeleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

}
