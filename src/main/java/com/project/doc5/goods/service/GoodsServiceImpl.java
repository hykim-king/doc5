package com.project.doc5.goods.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doc5.goods.domain.GoodsInfoVO;
import com.project.doc5.goods.domain.GoodsOptionVO;
import com.project.doc5.goods.domain.GoodsVO;
import com.project.doc5.goods.domain.OptionTypeVO;
import com.project.doc5.mapper.GoodsMapper;
import com.project.doc5.mypage.domain.MypageCartGoodsOptionVO;
import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.user.domain.UserVO;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Override
	public List<GoodsVO> doRetrieve(String cate) {
		return goodsMapper.doRetrieve(cate);
	}

	@Override
	public GoodsVO doSelectOne(GoodsVO param) {
		return goodsMapper.doSelectOne(param);
	}

	@Override
	public int doCartSave(GoodsVO param) {
		log.debug("┌──────────────────────────┐");
		log.debug("│doCartSave                │");
		log.debug("└──────────────────────────┘");
		log.debug("1.param:{}",param);
		int seq = 0;
		String branchCode = "s0001";
		String orderNo = "";
		String tumblerFl = "N";
		String cancelDt = "";
		String regDt = "";
		String modDt = "";
		double totalGoodsTotalPrice = 0.0;
		
		
		MypageCartVO mypageCartVO = new MypageCartVO(seq, param.getGoodsNo(), branchCode, orderNo, param.getUserId(), param.getGoodsName(), param.getGoodsPrice()
				, param.getGoodsCnt(),tumblerFl, param.getHotFl(), param.getHotPrice(), param.getIceFl(), param.getIcePrice(), param.getOrderType(),cancelDt, regDt, modDt, totalGoodsTotalPrice, null);
		log.debug("mypageCartVO : {}",mypageCartVO);
		
		log.debug(param.getOptions());
		goodsMapper.doCartSave(mypageCartVO);
		
		log.debug("mypageCartVO.getSeq : {}",mypageCartVO.getSeq());
		if(param.getOptions() != null) {
			
			MypageCartGoodsOptionVO mypageCartGoodsOptionVO = new MypageCartGoodsOptionVO();

			
			String[] tmpOptions = param.getOptions().split(",");
			for (String vo : tmpOptions) {
				// 정규식 예약어인 ^와 | 앞에 \\를 붙여줍니다.
				String[] tmpSubOptions = vo.split("\\^\\|\\^");

				// 반드시 배열 크기를 체크해야 안전합니다 (데이터가 없을 경우 방지)
				if (tmpSubOptions.length >= 2) {
				    try {
				    	String goodsType = "N";
				    	if(param.getHotFl().equals("Y")) {
				    		goodsType = "H";
				    	}else if(param.getIceFl().equals("Y")) {
				    		goodsType = "I";
				    	}
				        // String을 숫자로 변환
				        double price = Double.parseDouble(tmpSubOptions[1]);
				        mypageCartGoodsOptionVO.setGoodsType(goodsType);
				        mypageCartGoodsOptionVO.setGoodsNo(param.getGoodsNo());
				        mypageCartGoodsOptionVO.setcSeq(mypageCartVO.getSeq());
				        mypageCartGoodsOptionVO.setOptionName(tmpSubOptions[0]);
				        mypageCartGoodsOptionVO.setOptionPrice(price);
				        
				        goodsMapper.doCartGoodsOptionSave(mypageCartGoodsOptionVO);
				        
				        log.debug("seq : {} | 옵션명 : {} | 가격 : {}", mypageCartVO.getSeq(), tmpSubOptions[0], price);
				    } catch (NumberFormatException e) {
				        log.error("가격 데이터가 숫자가 아닙니다: {}", tmpSubOptions[1]);
				    }
				} else {
				    log.warn("구분자(^|^)가 포함되지 않은 올바르지 않은 데이터입니다: {}", vo);
				}
			}
		}
//		
//		mypageCartGoodsOptionVO.setOption_name(param.getOptions());
		return mypageCartVO.getSeq();
//		return goodsMapper.doCartSave(param);
	}

	@Override
	public List<GoodsInfoVO> doGoodsInfoSelect(GoodsInfoVO param) {
		return goodsMapper.doGoodsInfoSelect(param);
	}

	@Override
	public List<OptionTypeVO> doOptionTypeSelect(OptionTypeVO param) {
		return goodsMapper.doOptionTypeSelect(param);
	}

	@Override
	public List<GoodsOptionVO> doGoodsOptionSelect(GoodsOptionVO param) {
		return goodsMapper.doGoodsOptionSelect(param);
	}

}
