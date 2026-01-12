package com.project.doc5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doc5.board.domain.BoardVO;
import com.project.doc5.cmn.WorkDiv;
import com.project.doc5.mypage.domain.MypageOrderVO;

@Mapper
public interface MypageOrderMapper extends WorkDiv<MypageOrderVO> {
	
	// 전체조회:O
	List<BoardVO> getAll();
		
	// 전체 데이터 건수 조회:O
	int getCount();

	// 다건입력:O
	int saveAll();

	// 전체삭제:O
	int deleteAll();
	
	List<MypageOrderVO> doDetailOrder(MypageOrderVO param);

}
