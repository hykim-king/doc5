package com.project.doc5.order.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.project.doc5.mypage.domain.MypageCartVO;

@Service
public class OrderServiceImpl implements OrderService {

	final Logger log = LogManager.getLogger(getClass());
	
	@Override
	public List<MypageCartVO> doList(String seq, String orderType) {
		
		String[] unitSeqs = seq.split(",");
		
		// 2. 개수와 상관없이 루프를 돌며 처리 (단건이면 1번만 실행됨)
		for (String seqs : unitSeqs) {
		    if (seq != null && !seqs.trim().isEmpty()) {
		        // VO에 각각의 seq 설정
		        
		        
		        log.debug("삭제 대상 seq : {}", seqs);
		        
		        
		    }
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doUpdate(MypageCartVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

}
