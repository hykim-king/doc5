package com.project.doc5.mypage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.doc5.cmn.DTO;
import com.project.doc5.mapper.MypageCartMapper;
import com.project.doc5.mypage.domain.MypageCartVO;

public class MypageCartServiceIimpl implements MypageCartService {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	MypageCartMapper mypageCartMapper;
	
	public MypageCartServiceIimpl() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│MypageCartServiceIimpl()  │");
		log.debug("└──────────────────────────┘");	
	}

	@Override
	public List<MypageCartVO> doRetrieve(DTO dto) {
		return mypageCartMapper.doRetrieve(dto);
	}

	@Override
	public int doUpdate(MypageCartVO param) {
		return mypageCartMapper.doUpdate(param);
	}

	@Override
	public int doDelete(MypageCartVO param) {
		return mypageCartMapper.doDelete(param);
	}

	@Override
	public MypageCartVO doSelectOne(MypageCartVO param) {
		return mypageCartMapper.doSelectOne(param);
	}

	@Override
	public int doSave(MypageCartVO param) {
		return mypageCartMapper.doSave(param);
	}

}
