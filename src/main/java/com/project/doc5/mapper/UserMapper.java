/**
 * 
 */
package com.project.doc5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doc5.cmn.WorkDiv;
import com.project.doc5.user.domain.UserVO;

/**
 * @author user
 *
 */
@Mapper
public interface UserMapper extends WorkDiv<UserVO> {

	// 전체조회:O
	List<UserVO> getAll();
	
	// 전체 데이터 건수 조회:O
	int getCount();

	// 다건입력:O
	int saveAll();

	// 전체삭제:O
	int deleteAll();
}
