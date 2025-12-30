package com.project.doc5.goods.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.doc5.cmn.MenuVO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.mapper.GoodsMapper;
import com.project.doc5.mapper.MenuMapper;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	MenuMapper menuMapper;
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@GetMapping(value = "/goodsList.do")  
	public String goodsList(@RequestParam(required = false, defaultValue = "m_rec") String cate
			, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│goodsList()               │");
		log.debug("└──────────────────────────┘");
		log.debug("1.cate:{}",cate);
		
		String cateTitle = "";
		
		switch(cate){
			case "m_rec":
				cateTitle = "추천 상품";
				break;
			case "001":
				cateTitle = "커피";
				break;
			case "002":
				cateTitle = "디카페인";
				break;
			case "003":
				cateTitle = "음료";
				break;
			case "004":
				cateTitle = "티";
				break;
			case "005":
				cateTitle = "푸트";
				break;
			case "006":
				cateTitle = "상품";
				break;
		}
	
		
		List<MenuVO> cateList = menuMapper.getMenuAll();
		log.debug(cateList);
		
		model.addAttribute("cateList", cateList );
		model.addAttribute("cate", cate );
		model.addAttribute("cateTitle", cateTitle );
		return "/goods/goods_list";
	}

	@GetMapping(value = "/goodsView.do") 
	public String goodsView(@RequestParam(required = false, defaultValue = "10000") int goodsNo
			, Model model) {

		log.debug("┌──────────────────────────┐");
		log.debug("│goodsView()               │");
		log.debug("└──────────────────────────┘");
		
		log.debug("1.goodsNo:{}",goodsNo);
		
		GoodsVO goodsVO = goodsMapper.SelectGoods(goodsNo);
		
		log.debug("1.goodsVO:{}",goodsVO);
		model.addAttribute("goodsVO", goodsVO );
		
		
		return "/goods/goods_view"; 
	}
}
