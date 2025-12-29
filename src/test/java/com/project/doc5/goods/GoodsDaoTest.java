package com.project.doc5.goods;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import com.project.doc5.goods.domain.CategoryJoinVO;
import com.project.doc5.goods.domain.CategoryVO;
import com.project.doc5.goods.domain.GoodsOptionVO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.goods.domain.OptionTypeVO;
import com.project.doc5.mapper.GoodsMapper;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class GoodsDaoTest {

	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	ApplicationContext  context;
	

	@Autowired
	GoodsMapper goodsMapper;
	GoodsVO goodsVO;
	CategoryVO categoryVO;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──setup───────────────────│");
		log.debug("└──────────────────────────┘");		
		
		goodsVO=new GoodsVO();
		categoryVO=new CategoryVO();
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│─tearDown─────────────────│");
		log.debug("└──────────────────────────┘");			
	}
	
	
	//@Disabled
	@Test
	void doSelectGoodsWithOptions() {
		log.debug("┌──────────────────────────┐");
		log.debug("│doSelectGoodsWithOptions()│");
		log.debug("└──────────────────────────┘");	
		
		//param설정
		goodsVO.setGoodsNo(10000);
		
		//dao호출
		GoodsVO outVO=goodsMapper.SelectGoodsWithOptions(goodsVO.getGoodsNo());
		
		
		for (OptionTypeVO optType : outVO.getOptionTypeVO()) {

		    log.debug("▶ OptionTypeVO = seq:{}, optionName:{}, type:{}, hidden:{}",
		            optType.getSeq(),
		            optType.getGoodsOptionName(),
		            optType.getGoodsType(),
		            optType.getHiddenFl()
		    );

		    // GoodsOptionVO 출력
		    if (optType.getGoodsOptionVO() != null && !optType.getGoodsOptionVO().isEmpty()) {
		        for (GoodsOptionVO opt : optType.getGoodsOptionVO()) {
		            log.debug("   └ GoodsOptionVO = seq:{}, optionName:{}, addPrice:{}, hidden:{}",
		                    opt.getSeq(),
		                    opt.getOtSeq(),
		                    opt.getOptionName(),
		                    opt.getOptionPrice()
		            );
		        }
		    } else {
		        log.debug("   └ GoodsOptionVO = (empty)");
		    }
		}
				
		log.debug(outVO.getOptionTypeVO());
		log.debug("goodsVO:{}",outVO);
		assertNotNull(outVO);
		
	}
	
	
	//@Disabled
	@Test
	void doSelectGoodsCategory() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─doSelectGoodsCategory()  │");
		log.debug("└──────────────────────────┘");	
		
		//param설정
		categoryVO.setCode("001");
		
		//dao호출
		List<GoodsVO> outvo =goodsMapper.SelectGoodsCategory(categoryVO.getCode());
		
		
	    assertNotNull(outvo, "조회 결과 List는 null이면 안 됩니다.");
	    assertTrue(outvo.size() > 0, "카테고리에 해당하는 상품이 1개 이상 있어야 합니다.");

	    log.debug("조회된 상품 수: {}", outvo.size());

	    // 4. 결과 상세 로그 + 카테고리 검증
	    for (GoodsVO goods : outvo) {

	        log.debug("▶ GoodsVO = goodsNo:{}, name:{}, hidden:{}",
	                goods.getGoodsNo(),
	                goods.getGoodsName(),
	                goods.getHiddenFl()
	        );

	        // 카테고리 리스트 검증
	        assertNotNull(goods.getCategoryJoinVO(), "CategoryJoinVO는 null이면 안 됩니다.");
	        assertTrue(goods.getCategoryJoinVO().size() > 0,
	                "상품은 최소 1개 이상의 카테고리를 가져야 합니다.");

	        boolean hasCategory = false;

	        for (CategoryJoinVO cj : goods.getCategoryJoinVO()) {
	            log.debug("   └ CategoryJoinVO = code:{}, hidden:{}",
	                    cj.getCode(),
	                    cj.getHiddenFl()
	            );

	            if (categoryVO.getCode().equals(cj.getCode())) {
	                hasCategory = true;
	            }
	        }
	    }
	}
	
	
	@Disabled
	@Test
	void doSelectGoods() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─doSelectGoods()          │");
		log.debug("└──────────────────────────┘");	
		
		//param설정
		goodsVO.setGoodsNo(10000);
		
		//dao호출
		GoodsVO outVO=goodsMapper.SelectGoods(goodsVO.getGoodsNo());
		
		
		log.debug("goodsVO:{}",outVO);
		assertNotNull(outVO);
		
	}
	
	
	

	
	@Disabled
	@Test
	void doSelectOptiontype() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─doSelectOptiontype()     │");
		log.debug("└──────────────────────────┘");	
		
		//param설정
		goodsVO.setGoodsNo(10000);
		
		//dao호출
		GoodsVO outVO=goodsMapper.SelectOptiontype(goodsVO.getGoodsNo());
		
		
		log.debug("goodsVO:{}",outVO);
		assertNotNull(outVO);
		
	}
	
	@Disabled
	@Test
	void doSelectGoodsOption() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─SelectGoodsOption()      │");
		log.debug("└──────────────────────────┘");	
		
		//param설정
		goodsVO.setGoodsNo(10000);
		
		//dao호출
		GoodsVO outVO=goodsMapper.SelectGoodsOption(goodsVO.getGoodsNo());
		
		
		log.debug("goodsVO:{}",outVO);
		assertNotNull(outVO);
		
	}
	
	@Disabled
	@Test
	void doSelectGoodsInfo() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─doSelectGoodsInfo()      │");
		log.debug("└──────────────────────────┘");	
		
		//param설정
		goodsVO.setGoodsNo(10000);
		
		//dao호출
		GoodsVO outVO=goodsMapper.SelectGoodsInfo(goodsVO.getGoodsNo());
		
		
		log.debug("goodsVO:{}",outVO);
		assertNotNull(outVO);
		
	}
	
	@Disabled
	@Test
	void doSelectCategory() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─doSelectCategory()       │");
		log.debug("└──────────────────────────┘");	
		
		//param설정
		goodsVO.setGoodsNo(10000);
		
		//dao호출
		GoodsVO outVO=goodsMapper.SelectCategory(goodsVO.getGoodsNo());
		
		
		log.debug("goodsVO:{}",outVO);
		assertNotNull(outVO);
		
	}
	


	@Disabled
	@Test
	void beans() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─beans()                  │");
		log.debug("└──────────────────────────┘");
		
		log.debug("context:"+context);
		log.debug("goodsMapper:"+goodsMapper);
		
		assertNotNull(context);
		assertNotNull(goodsMapper);
	}

}
