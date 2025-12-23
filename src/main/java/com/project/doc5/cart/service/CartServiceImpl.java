package com.project.doc5.cart.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.doc5.cart.domain.CartOptionVO;
import com.project.doc5.cart.domain.CartVO;
import com.project.doc5.mapper.CartMapper;

public class CartServiceImpl implements CartService<CartVO> {
	
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
	public List<CartVO> doCartList(CartVO param) {
		return cartMapper.doCartList(param);
	}
	
	@Override
	public List<CartOptionVO> doCartOptionList(CartOptionVO param) {
		return cartMapper.doCartOptionList(param);
	}	

	@Override
	public int doCartUpdate(CartVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doCartDelete(CartVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cartDeleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

}
