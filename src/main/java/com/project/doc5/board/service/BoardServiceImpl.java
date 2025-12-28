package com.project.doc5.board.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doc5.board.domain.BoardVO;
import com.project.doc5.cmn.DTO;
import com.project.doc5.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	BoardMapper boardMapper;
	
	public BoardServiceImpl() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│BoardServiceImpl()        │");
		log.debug("└──────────────────────────┘");	
	}

	@Override
	public List<BoardVO> doRetrieve(DTO dto) {
		return boardMapper.doRetrieve(dto);
	}

	@Override
	public int doUpdate(BoardVO param) {
		return boardMapper.doUpdate(param);
	}

	@Override
	public int doDelete(BoardVO param) {
		return boardMapper.doDelete(param);
	}

	//조회 count증가 
	//단건조회 
	@Transactional
	public BoardVO upDoSelectOne(BoardVO param) {
		log.debug("┌────────────────────────────┐");
		log.debug("│doSelectOne()+updateReadCnt │");
		log.debug("└────────────────────────────┘");	
		
		log.debug("1.param : {}",param);
		
		//1. 조회 count 증가
		int flag = boardMapper.updateReadCnt(param);
		log.debug("2.flag : {}",flag);
		
		//2.단건 조회
		BoardVO boardVO = boardMapper.doSelectOne(param);
		log.debug("2.boardVO : {}",boardVO);
		
		return boardMapper.doSelectOne(param);
	}

	@Override
	public int doSave(BoardVO param) {
		//Mybatis가 시퀀스 값 넘겨줌 
		return boardMapper.doSave(param);
	}
	
	@Override
	public BoardVO doSelectOne(BoardVO param) {
		return boardMapper.doSelectOne(param);
	}

}
