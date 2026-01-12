package com.project.doc5.goods.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.doc5.cmn.MenuVO;
import com.project.doc5.cmn.MessageVO;
import com.project.doc5.goods.domain.GoodsInfoVO;
import com.project.doc5.goods.domain.GoodsSearchKeywordVO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.goods.domain.OptionTypeVO;
import com.project.doc5.goods.service.GoodsSearchService;
import com.project.doc5.goods.service.GoodsService;
import com.project.doc5.goods.service.MenuService;
import com.project.doc5.mapper.GoodsMapper;
import com.project.doc5.user.domain.UserVO;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	MenuService menuService;
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	GoodsSearchService goodsSearchService;
	
	@Autowired
	GoodsService goodsService;
	
	
	@PostMapping(value = "/goodsPs.do")  
	@ResponseBody
	public String goodsPs(GoodsVO param, @RequestParam(required = false, defaultValue = "cart") String orderType, HttpSession session) {
		log.debug("┌──────────────────────────┐");
		log.debug("│goodsPs                 ()│");
		log.debug("└──────────────────────────┘");
		log.debug("1.param:{}",param);
		
		UserVO userVO = (UserVO) session.getAttribute("sessionUser");
		
		
		MessageVO message = new MessageVO();
		String jsonString = "";
		
		if(null != userVO) {
			param.setUserId(userVO.getUserId());
			int seq = goodsService.doCartSave(param);
		
			if(seq > 0) {
				message.setFlag(1);
				message.setSeq(seq);
				message.setMessage("장바구니에 담겼습니다.");
			}else {
				message.setFlag(2);
				message.setSeq(seq);
				message.setMessage("장바구니 담기에 실패 했습니다.\n관리자에게 문의해 주세요.");
			}
		}else {
			message.setFlag(3);
			message.setSeq(0);
			message.setMessage("로그인 후 이용 가능합니다");
		}
		
		jsonString = new Gson().toJson(message);
		
		return jsonString;
	}
	
	@GetMapping(value = "/goodsList.do")  
	public String goodsList(@RequestParam(required = false, defaultValue = "001") String cate
			, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│goodsList()               │");
		log.debug("└──────────────────────────┘");
		log.debug("1.cate:{}",cate);
		
		List<MenuVO> cateList = menuService.doMenuAll();
		log.debug(cateList);
		
		model.addAttribute("cateList", cateList );
		model.addAttribute("cate", cate );
		

		return "/goods/goods_list";
	}
	
	
	@GetMapping(value = "/getAjaxGoodsOptionTypeList.do")  
//	@ResponseBody
	public String getAjaxGoodsOptionTypeList(int goodsNo, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│getAjaxGoodsOptionTypeList()│");
		log.debug("└──────────────────────────┘");
		log.debug("1.goodsNo:{}",goodsNo);
		
		String goodsType="N";
		OptionTypeVO optionTypeVO = new OptionTypeVO();
		optionTypeVO.setGoodsNo(goodsNo);
		optionTypeVO.setGoodsType(goodsType);

		List<OptionTypeVO> optionList = goodsService.doOptionTypeSelect(optionTypeVO);
		log.debug("optionList : {}",optionList);
		
		model.addAttribute("optionList", optionList );
		
		return "/goods/ajax_goods_option_type_list";
	}
	
	
	@GetMapping(value = "/getAjaxGoodsList.do")  
//	@ResponseBody
	public String getAjaxGoodsList(@RequestParam(required = false, defaultValue = "001") String cate
			, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│getAjaxGoodsList()        │");
		log.debug("└──────────────────────────┘");
		log.debug("1.cate:{}",cate);
		

		List<GoodsVO> goodsList = goodsService.doRetrieve(cate);
		log.debug("goodsList : {}",goodsList);
		
		model.addAttribute("list", goodsList );
		
		return "/goods/ajax_goods_list";
	}

	@GetMapping(value = "/goodsView.do") 
	public String goodsView(@RequestParam(required = false, defaultValue = "10000") int goodsNo
			, Model model) {

		log.debug("┌──────────────────────────┐");
		log.debug("│goodsView()               │");
		log.debug("└──────────────────────────┘");
		
		log.debug("1.goodsNo:{}",goodsNo);
		
		List<MenuVO> cateList = menuService.doMenuAll();
		log.debug(cateList);
		
		
		GoodsVO goodsVO = new GoodsVO();
		
		goodsVO.setGoodsNo(goodsNo);
		GoodsVO goodsOutVO = goodsService.doSelectOne(goodsVO);
		
		
		String goodsType="N";
		OptionTypeVO optionTypeVO = new OptionTypeVO();
		optionTypeVO.setGoodsNo(goodsNo);
		optionTypeVO.setGoodsType(goodsType);

		List<OptionTypeVO> optionList = goodsService.doOptionTypeSelect(optionTypeVO);
		log.debug("optionList : {}",optionList);
		
		
		GoodsInfoVO goodsInfoVO = new GoodsInfoVO();
		goodsInfoVO.setGoodsNo(goodsNo);
		goodsInfoVO.setGoodsType("I");
		List<GoodsInfoVO> goodsInfo = goodsService.doGoodsInfoSelect(goodsInfoVO);
		log.debug("1.goodsInfo:{}",goodsInfo);
		
		model.addAttribute("goodsInfo", goodsInfo );
		
		model.addAttribute("optionList", optionList );
		
		log.debug("1.goodsVO:{}",goodsOutVO);
		model.addAttribute("cateList", cateList );
		model.addAttribute("goodsVO", goodsOutVO );
		
		
		return "/goods/goods_view"; 
	}
	
	@GetMapping(value = "/goodsSearch.do")  
	public String goodsSearch(@RequestParam(required = false, defaultValue = "") String keyword
			, HttpSession session, Model model) {
		log.debug("┌──────────────────────────┐");
		log.debug("│goodsSearch()             │");
		log.debug("└──────────────────────────┘");
		log.debug("1.keyword:{}",keyword);
       
        UserVO userVO = (UserVO) session.getAttribute("sessionUser");
		
		String userId;
		
		if(userVO == null) {

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
			userId = userVO.getUserId();
		}
		
		log.debug("userId After: {}",userId);
		
		List<GoodsVO> list = goodsSearchService.goodsSearchKeywordUp(keyword, userId);
		
		log.debug("goodsSearch : {}",list);
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
		String tmpId;
		UserVO userVO = (UserVO) session.getAttribute("sessionUser");
		
		if(userVO == null) {

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
			userId = userVO.getUserId();
			tmpId = (String) session.getAttribute("sessionTmpId");
			if(tmpId != null) {
				session.setAttribute("sessionTmpId", null);
				GoodsSearchKeywordVO goodsSearchKeywordVO = new GoodsSearchKeywordVO();
				goodsSearchKeywordVO.setUserId(userId);
				goodsSearchKeywordVO.setTmpUserId(tmpId);
				goodsSearchService.goodsSearchUpdate(goodsSearchKeywordVO);
			}
			
		}
		
		log.debug("userId After: {}",userId);
		
		GoodsSearchKeywordVO gskVO = new GoodsSearchKeywordVO();
		gskVO.setUserId(userId);
		
		List<GoodsSearchKeywordVO> gsList = goodsSearchService.goodsSearchKeywordList(gskVO);
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
		
		UserVO userVO = (UserVO) session.getAttribute("sessionUser");
		
		if(userVO == null) {

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
			userId = userVO.getUserId();
		}

		
		GoodsSearchKeywordVO gskVO = new GoodsSearchKeywordVO(0, userId, keyword, "N","등록일", null);
		
		goodsSearchService.goodsSearchHiddenUpdate(gskVO);
		
		return null;
	}
}
