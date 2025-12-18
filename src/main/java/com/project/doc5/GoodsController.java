package com.project.doc5;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GoodsController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

	@RequestMapping(value="/goods/goodsList", method=RequestMethod.GET)
	public String goodsList(HttpServletRequest req, Locale locale, Model model) {
		logger.info("Goods List The client locale is {}.", locale);
		
		String cate = req.getParameter("cate");
		
		model.addAttribute("cate", cate );
		return "/goods/goods_list";
	}

	@RequestMapping(value="/goods/goodsView", method=RequestMethod.GET)
	public String goodsView(HttpServletRequest req, Locale locale, Model model) {
		logger.info("Goods View The client locale is {}.", locale);
		
		String cate = req.getParameter("cate");
		System.out.println(cate);
		
		model.addAttribute("cate", cate );
		
		return "/goods/goods_view"; 
	}
}
