package com.project.doc5.board.service;

import com.project.doc5.board.domain.BoardVO;
import com.project.doc5.cmn.WorkDiv;

public interface BoardService extends WorkDiv<BoardVO> {
	
	//단건 조회 + 조회 count증
	BoardVO upDoSelectOne(BoardVO param);
	
}
