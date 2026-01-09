package com.project.doc5.goods;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.goods.service.GoodsService;
import com.project.doc5.mapper.GoodsMapper;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class GoodsServiceTest {

	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	ApplicationContext  context;
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	GoodsService goodsService;
	

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
    //@Disabled
    void selectGoodsWithOptions() {
        log.debug("┌──────────────────────────┐");
        log.debug("│selectGoodsWithOptions()  │");
        log.debug("└──────────────────────────┘");

        // given
        int goodsNo = 10000; // 실제 존재하는 상품 번호

        // when
        GoodsVO outVO = goodsService.selectGoodsWithOptions(goodsNo);

        // then
        assertNotNull(outVO);
        assertEquals(goodsNo, outVO.getGoodsNo());

        log.debug("상품번호: {}", outVO.getGoodsNo());
        log.debug("상품명: {}", outVO.getGoodsName());

        if (outVO.getOptionTypeVO() != null) {
            outVO.getOptionTypeVO().forEach(optType -> {
                log.debug("▶ 옵션타입: {}", optType.getGoodsOptionName());

                if (optType.getGoodsOptionVO() != null) {
                    optType.getGoodsOptionVO().forEach(opt -> {
                        log.debug("   └ 옵션명: {}, 가격: {}",
                                opt.getOptionName(),
                                opt.getOptionPrice());
                    });
                }
            });
        }
    }

	
    @Test
    @Disabled
    void selectGoodsCategory() {
        log.debug("┌──────────────────────────┐");
        log.debug("│selectGoodsCategory()     │");
        log.debug("└──────────────────────────┘");

        // given
        String categoryCode = "001"; // 실제 존재하는 코드

        // when
        List<GoodsVO> list = goodsService.selectGoodsCategory(categoryCode);

        // then
        assertNotNull(list);
        assertFalse(list.isEmpty());

        list.forEach(goods -> {
            log.debug("상품번호: {}", goods.getGoodsNo());
            log.debug("상품명: {}", goods.getGoodsName());

            if (goods.getCategoryJoinVO() != null) {
                goods.getCategoryJoinVO().forEach(join -> {
                    log.debug("카테고리 코드: {}", join.getCode());
                });
            }
        });
    }

	
	@Test
	@Disabled
	void beans() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─beans()                  │");
		log.debug("└──────────────────────────┘");	
		
		log.debug("context:"+context);
		log.debug("goodsMapper:"+goodsMapper);
		log.debug("goodsService:"+goodsService);
		assertNotNull(context);
		assertNotNull(goodsMapper);
	}

}
