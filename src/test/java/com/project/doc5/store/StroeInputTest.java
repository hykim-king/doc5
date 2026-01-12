package com.project.doc5.store;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.project.doc5.branch.domain.BranchVO;
import com.project.doc5.branch.service.Distance;
import com.project.doc5.branch.service.Store;
import com.project.doc5.goods.domain.CategoryJoinVO;
import com.project.doc5.goods.domain.GoodsInfoVO;
import com.project.doc5.goods.domain.GoodsOptionVO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.goods.domain.OptionTypeVO;
import com.project.doc5.goods.service.GoodsDataInsert;
import com.project.doc5.mapper.DataInsertMapper;




@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class StroeInputTest {

	final Logger log = LogManager.getLogger(getClass());
	
	public static final String STORE_DATA = "./data/starbucks.json"; //스토어 저장 경로
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	DataInsertMapper dataInsertMapper;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──setup()─────────────────│");
		log.debug("└──────────────────────────┘");	
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──tearDown()──────────────│");
		log.debug("└──────────────────────────┘");	
	}
	
	//상품 일괄 등록
	//@Disabled
	@Test
	void goodsDataInsert() {
		log.debug("┌──────────────────────────┐");
		log.debug("│ goodsDataInsert()        │");
		log.debug("└──────────────────────────┘");	
		
		//상품 관련 DB 초기
		dataInsertMapper.deleteGoodsAll();
		dataInsertMapper.deleteGoodsInfoAll();
		dataInsertMapper.deleteOptionTypeAll();
		dataInsertMapper.deleteGoodsOptionAll();
		dataInsertMapper.deleteCategoryJoinAll();
		
		Gson gson = new Gson();
		String readStoreTmp = "";//이전 검색 리스트
		
		// JSON 파일 읽기
		String jsonString;
		
		//1차 카테고리, 2차 카테고리 설정  
		String cateCd1;
		String cateCd2;
		
		
		// Json파일 배열로 정의 
		List<String> jsonFiles = new ArrayList<>();
		
		jsonFiles.add("./data/goodsData001001.json"); // 커피 - 에스프레소 
		jsonFiles.add("./data/goodsData001002.json"); // 커피 - 라떼 
		jsonFiles.add("./data/goodsData001003.json"); // 커피 - 콜드브루 
		jsonFiles.add("./data/goodsData002001.json"); // 디카페인 - 에스프레소 
		jsonFiles.add("./data/goodsData002002.json"); // 디카페인 - 라떼 
		jsonFiles.add("./data/goodsData002003.json"); // 디카페인 - 콜드브루 
		jsonFiles.add("./data/goodsData003001.json"); // 음료 - 에이
		jsonFiles.add("./data/goodsData003002.json"); // 음료 - 프라페 
		jsonFiles.add("./data/goodsData003003.json"); // 음료 - 스무디&쥬스 
		jsonFiles.add("./data/goodsData003004.json"); // 음료 - 논-커피 라떼 
		jsonFiles.add("./data/goodsData004001.json"); // 티 - 티플레저 
		jsonFiles.add("./data/goodsData004002.json"); // 티 - 클래식 
		jsonFiles.add("./data/goodsData005001.json"); // 푸드 - 디저트 
		jsonFiles.add("./data/goodsData005002.json"); // 푸드 - 베이커리 
		jsonFiles.add("./data/goodsData005003.json"); // 푸드 - 케이크 
		jsonFiles.add("./data/goodsData006001.json"); // 상품 - 굿즈 
		jsonFiles.add("./data/goodsData006002.json"); // 상품 - 홈카페 
		jsonFiles.add("./data/goodsData006003.json"); // 상품 - 병음료 

		//상품 고유번호 초기화
		int n = 10000;
		
		//총 상품 수량 초기화 
		int count = 0;
		
		GoodsVO goodsVO = new GoodsVO();
		CategoryJoinVO cjVO = new CategoryJoinVO();

		try {
			for(String goodsDataJson : jsonFiles) {
				log.debug("goodsDataJson : {}",goodsDataJson);
				jsonString = new String(Files.readAllBytes(Paths.get(goodsDataJson)), StandardCharsets.UTF_8);
				GoodsDataInsert[] goodsDataInsert = gson.fromJson(jsonString, GoodsDataInsert[].class);
				
				count = dataInsertMapper.goodsAllCount();
				
				log.debug("count : {}",count);
				
				for(GoodsDataInsert gdi : goodsDataInsert) {
					
					goodsVO.setGoodsNo(n);
					goodsVO.setGoodsName(gdi.goods_name);
					goodsVO.setGoodsEngName(gdi.goods_eng_name);
					goodsVO.setShortDescription(gdi.goods_description);
					goodsVO.setAlleojiInfo(gdi.goods_allergy);
					goodsVO.setGoodsPrice(gdi.goods_price);
					goodsVO.setHotFl(gdi.hot_fl);
					goodsVO.setHotPrice(gdi.hot_price);
					goodsVO.setIceFl(gdi.ice_fl);
					goodsVO.setIcePrice(gdi.ice_price);
					goodsVO.setTumblerFl("N");
					goodsVO.setGoodsContents(gdi.goods_summary);
					goodsVO.setNewFl("N");
					goodsVO.setBestFl("N");
					goodsVO.setGoodsView(0);
					goodsVO.setHiddenFl("N");
					goodsVO.setRegId("doc5_tspark");
					goodsVO.setModId("doc5_tspark");
					
					
					
					if(!gdi.goods_fat.equals("")) {
						log.debug("포화지방 : {}",gdi.goods_fat);
						
						if(gdi.hot_fl.equals("Y")) {
							setGoodsInfo(n, "H", "포화지방", gdi.goods_fat);
						}
						
						if(gdi.ice_fl.equals("Y")) {
							setGoodsInfo(n, "I", "포화지방", gdi.goods_fat);
						}
					}
					
					if(!gdi.goods_sugar.equals("")) {
						log.debug("당류 : {}",gdi.goods_sugar);
						
						if(gdi.hot_fl.equals("Y")) {
							setGoodsInfo(n, "H", "당류", gdi.goods_sugar);
						}
						
						if(gdi.ice_fl.equals("Y")) {
							setGoodsInfo(n, "I", "당류", gdi.goods_sugar);
						}
					}
					
					if(!gdi.goods_sodium.equals("")) {
						log.debug("나트륨 : {}",gdi.goods_sodium);
						
						if(gdi.hot_fl.equals("Y")) {
							setGoodsInfo(n, "H", "나트륨", gdi.goods_sodium);
						}
						
						if(gdi.ice_fl.equals("Y")) {
							setGoodsInfo(n, "I", "나트륨", gdi.goods_sodium);
						}
					}
					
					if(!gdi.goods_protein.equals("")) {
						log.debug("단백질 : {}",gdi.goods_protein);
						
						if(gdi.hot_fl.equals("Y")) {
							setGoodsInfo(n, "H", "단백질", gdi.goods_protein);
						}
						
						if(gdi.ice_fl.equals("Y")) {
							setGoodsInfo(n, "I", "단백질", gdi.goods_protein);
						}
					}
					
					if(!gdi.goods_caffeine.equals("")) {
						log.debug("카페인 : {}",gdi.goods_caffeine);
						
						if(gdi.hot_fl.equals("Y")) {
							setGoodsInfo(n, "H", "카페인", gdi.goods_caffeine);
						}
						
						if(gdi.ice_fl.equals("Y")) {
							setGoodsInfo(n, "I", "카페인", gdi.goods_caffeine);
						}
					}
					
					
					log.debug("goodsVO : {}",goodsVO.toString());
					
					dataInsertMapper.doGoodsSave(goodsVO);
					
					cateCd1 = goodsDataJson.substring(goodsDataJson.length()-11,goodsDataJson.length()-8);
					cateCd2 = goodsDataJson.substring(goodsDataJson.length()-11,goodsDataJson.length()-5);
					
					if(cateCd1.equals("001") || cateCd1.equals("002") ) {
					
						//옵션 저장 
						int seq = 0;
						int otSeq = 0;
						otSeq = setGoodsOptionType(n, "샷 선택");
						setGoodsOption(0, otSeq, "0", "연하게", 0, "N");
						setGoodsOption(0, otSeq, "1", "샷 추가", 600, "N");
						setGoodsOption(0, otSeq, "2", "2샷 추가", 1200, "N");
						
						otSeq = setGoodsOptionType(n, "저당 스테비아 슈가추가");
						setGoodsOption(0, otSeq, "0", "저당 스테비아 추가", 600, "N");
						
						otSeq = setGoodsOptionType(n, "당도 선택");
						setGoodsOption(0, otSeq, "0", "연유 추가", 700, "N");
						
						otSeq = setGoodsOptionType(n, "토핑 선택");
						setGoodsOption(0, otSeq, "0", "휘핑추가", 500, "N");
						setGoodsOption(0, otSeq, "1", "타피오카 펄 추가", 700, "N");
						setGoodsOption(0, otSeq, "2", "초코젤라또 추가", 700, "N");
		
					}else if(cateCd1.equals("003")){
						//옵션 저장 
						int seq = 0;
						int otSeq = 0;
						otSeq = setGoodsOptionType(n, "논-커피 샷 선택");
						setGoodsOption(0, otSeq, "0", "연하게", 0, "N");
						setGoodsOption(0, otSeq, "1", "샷 추가", 600, "N");
						setGoodsOption(0, otSeq, "2", "2샷 추가", 1200, "N");
						setGoodsOption(0, otSeq, "3", "디카페인 샷 추가", 1000, "N");
						setGoodsOption(0, otSeq, "4", "디카페인 2샷 추가", 2000, "N");
						
						otSeq = setGoodsOptionType(n, "당도 선택");
						setGoodsOption(0, otSeq, "0", "바닐라시럽추가", 500, "N");
						setGoodsOption(0, otSeq, "1", "카라멜시럽추가", 500, "N");
						setGoodsOption(0, otSeq, "2", "헤이즐넛시럽추가", 500, "N");
						setGoodsOption(0, otSeq, "3", "라이트바닐라시럽추가", 800, "N");
						setGoodsOption(0, otSeq, "4", "꿀 추가", 700, "N");
						
						otSeq = setGoodsOptionType(n, "토핑 선택");
						setGoodsOption(0, otSeq, "0", "휘핑추가", 500, "N");
						setGoodsOption(0, otSeq, "1", "타피오카 펄 추가", 700, "N");
		
					}
					
					cjVO.setCode(cateCd1);
					cjVO.setGoodsNo(n);
					cjVO.setHiddenFl("N");
					
					dataInsertMapper.doCategoryJoinSave(cjVO);
					
					cjVO.setCode(cateCd2);
					dataInsertMapper.doCategoryJoinSave(cjVO);
					n++;	
				
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void setGoodsOption(int seq, int otSeq, String step, String optionName, double optionPrice, String fl) {
		GoodsOptionVO goVO = new GoodsOptionVO(seq, otSeq, step, optionName, optionPrice, fl);
		log.debug("goVO : {}",goVO);
		dataInsertMapper.doGoodsOptionSave(goVO);
		
	
	}
	
	private int setGoodsOptionType(int goodsNo, String optionName) {
		int seq = 0;
		OptionTypeVO optionTypeVO = new OptionTypeVO();
		
		optionTypeVO.setGoodsNo(goodsNo);
		optionTypeVO.setGoodsOptionName(optionName);
		optionTypeVO.setGoodsType("N");
		optionTypeVO.setListSort(0);
		optionTypeVO.setHiddenFl("N");
		
		seq = dataInsertMapper.doGoodsOptionTypeSave(optionTypeVO);
		log.debug("optionTypeVO : {}", optionTypeVO.toString());
		
		return optionTypeVO.getSeq();
		
	}
	
	private void setGoodsInfo(int godosNo, String type, String title, String text) {
		GoodsInfoVO goodsInfoVO = new GoodsInfoVO();
		
		goodsInfoVO.setGoodsNo(godosNo);
		goodsInfoVO.setGoodsType(type);
		goodsInfoVO.setProductInfoSort(0);
		goodsInfoVO.setProductInfoTitle(title);
		goodsInfoVO.setProductInfoText(text);
		
		log.debug("goodsInfoVO : {}", goodsInfoVO);
		dataInsertMapper.doGoodsInfoSave(goodsInfoVO);
	}
	
	//스토어 일괄 등록 
	//@Disabled
	@Test
	void storeDataInsert() {
		log.debug("┌──────────────────────────┐");
		log.debug("│ storeDataInsert()        │");
		log.debug("└──────────────────────────┘");	
		
		Gson gson = new Gson();
		Distance distance = new Distance();
		
		// JSON 파일 읽기
		String jsonString;
		
		try {
			jsonString = new String(Files.readAllBytes(Paths.get(STORE_DATA)), StandardCharsets.UTF_8);
			Store[] stores =gson.fromJson(jsonString, Store[].class);
			
			int n = 1;
			
			BranchVO branchVO = new BranchVO();
			dataInsertMapper.deleteBranchAll();
			for(Store store : stores) {
				String formatted = String.format("%04d", n);
				
				branchVO.setBranchCode("s"+formatted);
				branchVO.setBranchName(store.name);
				branchVO.setLat(Double.parseDouble(store.latitude));
				branchVO.setLng(Double.parseDouble(store.longitude));
				branchVO.setZoneCode("");
				branchVO.setAddress1(store.address);
				branchVO.setAddress2("");
				branchVO.setTel("1522-3232");
				branchVO.setHiddenFl("N");
				
				log.debug(branchVO.toString());
				
				n++;
				
				dataInsertMapper.doSave(branchVO);
				
			}
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Disabled
	@Test
	void beans() {
		log.debug("context:"+context);
		assertNotNull(context);
		
		log.debug("dataInsertMapper:"+dataInsertMapper);
		assertNotNull(dataInsertMapper);
		
		
	}

}
