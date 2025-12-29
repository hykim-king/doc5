package com.project.doc5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doc5.board.domain.BoardVO;
import com.project.doc5.cmn.WorkDiv;
import com.project.doc5.mypage.domain.MypageCartVO;

@Mapper
public interface MypageCartMapper extends WorkDiv<MypageCartVO> {
	
	// 전체조회:O
	List<MypageCartVO> getAll();
			
	// 전체 데이터 건수 조회:O
	int getCount();

	// 다건입력:O
	int saveAll();

	// 전체삭제:O
	int deleteAll();
	
}
