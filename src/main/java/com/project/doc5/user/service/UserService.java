package com.project.doc5.user.service;

import java.sql.SQLException;
import java.util.List;

import com.project.doc5.cmn.DTO;
import com.project.doc5.user.domain.UserVO;


public interface UserService<UserVo> {
	/**
	 * 목록조회
	 * @param dto
	 * @return
	 */
	List<UserVo> doRetrieve(DTO dto);

	/**
	 * 단건수정
	 * @param param
	 * @return
	 */
	int doUpdate(UserVo param);
	int doUpdateNotPass(UserVo param);
	
	/**
	 * 단건삭제
	 * @param param
	 * @return
	 */
	int doDelete(UserVo param);

	/**
	 * 단건조회
	 * 
	 * @param param
	 * @return UserVO
	 */
	UserVO doSelectOne(UserVo param) ;

	/**
	 * 단건저장
	 * 
	 * @param param
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doSave(UserVo param);
	
	/**
	 * id존재 체크
	 * @param param
	 * @return
	 */
	int userIdCheck(UserVo param);
	
	
	/**
	 * id,비번 체크
	 * @param param
	 * @return
	 */
	int userPasswordCheck(UserVo param);
	
	/**
	 * 로그인
	 * @param param
	 * @return
	 */
	UserVO userLogin(UserVo param);
}
