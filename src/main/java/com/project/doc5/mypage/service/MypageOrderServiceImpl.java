package com.project.doc5.mypage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.doc5.cmn.DTO;
import com.project.doc5.mapper.MypageOrderMapper;
import com.project.doc5.mypage.domain.MypageCartVO;
import com.project.doc5.mypage.domain.MypageOrderVO;

public class MypageOrderServiceImpl implements MypageOrderService {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	MypageOrderMapper mypageOrderMapper;
	
	
	public MypageOrderServiceImpl() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│MpageServiceImpl()        │");
		log.debug("└──────────────────────────┘");
	}

	@Override
	public List<MypageOrderVO> doRetrieve(DTO dto) {
		return null;
	}

	@Override
	public int doUpdate(MypageOrderVO param) {
		return 0;
	}

	@Override
	public int doDelete(MypageOrderVO param) {
		return 0;
	}

	@Override
	public MypageOrderVO doSelectOne(MypageOrderVO param) {
		return null;
	}

	@Override
	public int doSave(MypageOrderVO param) {
		return 0;
	}

}
