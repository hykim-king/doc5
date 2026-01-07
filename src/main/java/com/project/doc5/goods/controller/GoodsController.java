package com.project.doc5.goods.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.doc5.cmn.MenuVO;
import com.project.doc5.goods.domain.GoodsSearchKeywordVO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.mapper.GoodsMapper;
import com.project.doc5.mapper.GoodsSearchMapper;
import com.project.doc5.mapper.MenuMapper;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	MenuMapper menuMapper;
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	GoodsSearchMapper goodsSearchMapper;
	
	
	@PostMapping(value = "/goodsPs.do")  
//	@ResponseBody
	public String goodsPs(GoodsVO param, @RequestParam(required = false) List<String> optionInfo
			) {
		log.debug("┌──────────────────────────┐");
		log.debug("│goodsPs                 ()│");
		log.debug("└──────────────────────────┘");
		log.debug("1.param:{}",param);
		
		log.debug("1.option:{}",optionInfo);
		
		
//		goodsServiceImpl.cartSave(param, optionInfo);
//		goodsServiceImpl.cartOptionSave(optionInfo);
		
		
		
		
		
		return null;
	}
	
	@GetMapping(value = "/goodsList.do")  
	public String goodsList(@RequestParam(required = false, defaultValue = "m_rec") String cate
			, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│goodsList()               │");
		log.debug("└──────────────────────────┘");
		log.debug("1.cate:{}",cate);
		
		String cateTitle = "";
		
//		if (cate == null || "null".equals(cate)){
//			cate = "m_rec";
//		}
		
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
		
		List<MenuVO> cateList = menuMapper.getMenuAll();
		log.debug(cateList);
		
		GoodsVO goodsVO = goodsMapper.SelectGoods(goodsNo);
		
		log.debug("1.goodsVO:{}",goodsVO);
		model.addAttribute("cateList", cateList );
		model.addAttribute("goodsVO", goodsVO );
		
		
		return "/goods/goods_view"; 
	}
	
	@GetMapping(value = "/goodsSearch.do")  
	public String goodsSearch(@RequestParam(required = false, defaultValue = "") String keyword
			, final HttpServletRequest request, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│goodsSearch()             │");
		log.debug("└──────────────────────────┘");
		log.debug("1.keyword:{}",keyword);
		
		HttpSession session = request.getSession();
		
		String userId;
		
		if(session.getAttribute("sessionUserId") == null) {

			if(session.getAttribute("sessionTmpId") != null) {
				userId = session.getAttribute("sessionTmpId")+"";
			}else {
				Date now = new Date(); // 현재 날짜와 시간
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
		        String formattedDate = sdf.format(now);
		        session.setAttribute("sessionTmpId", formattedDate);
		        userId = formattedDate;
			}
		}else {
			userId = session.getAttribute("sessionUserId")+"";
		}
		
		log.debug("userId After: {}",userId);
		
		List<GoodsVO> list = goodsSearchMapper.goodsSearch(keyword);
		
		GoodsSearchKeywordVO gskVO = new GoodsSearchKeywordVO(0, userId, keyword, "N","등록일", null);
		
		goodsSearchMapper.goodsSearchHiddenUpdate(gskVO);
		log.debug("gskVO: {}",gskVO);
		//키워드 저장 
		goodsSearchMapper.goodsSearchSave(gskVO);
		
		log.debug("list : {}",list);
		model.addAttribute("keyword", keyword );
		model.addAttribute("list", list );
		
		
		return "/goods/goods_search";
	}
	
	@RequestMapping(value = "/goodsSearchKeywordList.do")  
	public String goodsSearchKeywordList(final HttpServletRequest request, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│goodsSearchList()         │");
		log.debug("└──────────────────────────┘");
		
		HttpSession session = request.getSession();
		
		String userId;
		
		if(session.getAttribute("sessionUserId") == null) {

			if(session.getAttribute("sessionTmpId") != null) {
				userId = session.getAttribute("sessionTmpId")+"";
			}else {
				Date now = new Date(); // 현재 날짜와 시간
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
		        String formattedDate = sdf.format(now);
		        session.setAttribute("sessionTmpId", formattedDate);
		        userId = formattedDate;
			}
		}else {
			userId = session.getAttribute("sessionUserId")+"";
		}
		
		log.debug("userId After: {}",userId);
		
		
		
		GoodsSearchKeywordVO gskVO = new GoodsSearchKeywordVO();
		gskVO.setUserId(userId);
		
		
		List<GoodsSearchKeywordVO> gsList = goodsSearchMapper.goodsSearchKeywordList(gskVO);
		log.debug("gsList : {}",gsList);
		model.addAttribute("gsList", gsList );
		
		
		return "/goods/goods_search_keyword_list";
	}
	
	@PostMapping(value = "/goodsSearchKeywordHiddeh.do")  
	@ResponseBody
	public String goodsSearchKeywordHiddeh(@RequestParam(required = false, defaultValue = "") String keyword
			, final HttpServletRequest request, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│goodsSearchKeywordHiddeh()│");
		log.debug("└──────────────────────────┘");
		log.debug("1.keyword:{}",keyword);
		
		HttpSession session = request.getSession();
		
		String userId;
		
		if(session.getAttribute("sessionUserId") == null) {

			if(session.getAttribute("sessionTmpId") != null) {
				userId = session.getAttribute("sessionTmpId")+"";
			}else {
				Date now = new Date(); // 현재 날짜와 시간
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
		        String formattedDate = sdf.format(now);
		        session.setAttribute("sessionTmpId", formattedDate);
		        userId = formattedDate;
			}
		}else {
			userId = session.getAttribute("sessionUserId")+"";
		}

		
		GoodsSearchKeywordVO gskVO = new GoodsSearchKeywordVO(0, userId, keyword, "N","등록일", null);
		
		goodsSearchMapper.goodsSearchHiddenUpdate(gskVO);
		
		return null;
	}
}
