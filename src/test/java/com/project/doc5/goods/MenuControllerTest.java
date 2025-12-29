package com.project.doc5.goods;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import com.project.doc5.cmn.MenuVO;
import com.project.doc5.mapper.MenuMapper;
import com.project.doc5.user.domain.UserVO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class MenuControllerTest {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	MenuMapper menuMapper;
	
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

	//상품 메뉴 리스트
	@Test
	void getMenu() {
		//3.
		List<MenuVO> list = menuMapper.getMenuAll();
		log.debug(list);
	}
	
	@Disabled
	@Test
	void beans() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─beans()                  │");
		log.debug("└──────────────────────────┘");	
		
		log.debug("webApplicationContext:"+webApplicationContext);
		log.debug("menuMapper:"+menuMapper);
		assertNotNull(webApplicationContext);
		assertNotNull(menuMapper);
		
	}
}
