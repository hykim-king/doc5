package com.project.doc5.mapper;

import java.util.List;

import com.project.doc5.board.domain.BoardVO;
import com.project.doc5.cmn.WorkDiv;

public interface BoardMapper extends WorkDiv<BoardVO> {

	// 전체조회:O
	List<BoardVO> getAll();
	
	// 전체 데이터 건수 조회:O
	int getCount();

	// 다건입력:O
	int saveAll();

	// 전체삭제:O
	int deleteAll();
	
	// 조회수 증가
	int updateReadCnt(BoardVO param);
}
