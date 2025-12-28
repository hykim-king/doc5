package com.project.doc5.cmn;

import java.sql.SQLException;
import java.util.List;

import com.project.doc5.user.domain.UserVO;

public interface WorkDiv<T> {
	/**
	 * 목록조회
	 * @param dto
	 * @return
	 */
	List<T> doRetrieve(DTO dto);

	/**
	 * 단건수정
	 * @param param
	 * @return
	 */
	int doUpdate(T param);
	
	/**
	 * 단건삭제
	 * @param param
	 * @return
	 */
	int doDelete(T param);

	/**
	 * 단건조회
	 * 
	 * @param param
	 * @return UserVO
	 */
	T doSelectOne(T param) ;

	/**
	 * 단건저장
	 * 
	 * @param param
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doSave(T param);
	
}
