package com.project.doc5.cart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.project.doc5.cart.domain.CartOptionVO;
import com.project.doc5.cart.domain.CartVO;
import com.project.doc5.mapper.CartMapper;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class CartControllerTest {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	CartMapper cartMapper;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──setup───────────────────│");
		log.debug("└──────────────────────────┘");		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│─tearDown()               │");
		log.debug("└──────────────────────────┘");		
	}

	@Test
	void cartList() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─cartList()               │");
		log.debug("└──────────────────────────┘");	
		
		CartVO cartVO = new CartVO();
		
		cartVO.setUserId("test");
		log.debug("cartvo : {}",cartVO);
		
		List<CartVO> list = cartMapper.doCartList(cartVO);

		log.debug("cartList:{}",list);
		
		CartOptionVO cartOptionVO = new CartOptionVO();
		List<CartOptionVO> optionList = null;

		for(CartVO vo : list) {
			log.debug("seq : {}",vo.getSeq());
			cartOptionVO.setcSeq(vo.getSeq());
			log.debug("cartOptionVO : {}",cartOptionVO);
			optionList = cartMapper.doCartOptionList(cartOptionVO);
			log.debug("optionList : {}",optionList);
		}
	}
	
	@Disabled
	@Test
	void beans() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─beans()                  │");
		log.debug("└──────────────────────────┘");	
		
		log.debug("webApplicationContext:"+webApplicationContext);
		log.debug("cartMapper:"+cartMapper);
		
		assertNotNull(webApplicationContext);
		assertNotNull(cartMapper);
		
	}

}

