package com.project.doc5.user.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doc5.cmn.DTO;
import com.project.doc5.mapper.UserMapper;
import com.project.doc5.user.domain.UserVO;

@Service
public class UserServicelmpl implements UserService<UserVO> {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	UserMapper userMapper; 
	
	public UserServicelmpl() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│UserServicelmpl()         │");
		log.debug("└──────────────────────────┘");	
	}

	@Override
	public List<UserVO> doRetrieve(DTO dto) {
		return userMapper.doRetrieve(dto);
	}

	@Override
	public int doUpdate(UserVO param) {
		return userMapper.doUpdate(param);
	}
	
	@Override
	public int doUpdateNotPass(UserVO param) {
		return userMapper.doUpdateNotPass(param);
	}

	@Override
	public int doDelete(UserVO param) {
		return userMapper.doDelete(param);
	}

	@Override
	public UserVO doSelectOne(UserVO param) {
		return userMapper.doSelectOne(param);
	}

	@Override
	public int doSave(UserVO param) {
		return userMapper.doSave(param);
	}
	
	@Override
	public UserVO userLogin(UserVO param) {
		UserVO outVO = null;
		
		int count = userMapper.userIdCheck(param);
		
		if(0 == count) {
			throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
		}
		
		count = userMapper.userPasswordCheck(param);
		if(0 == count) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
		
		outVO = userMapper.doSelectOne(param);
		log.debug("outVO:{}",outVO);
		
		outVO.setPassword("");
		
		return outVO;
	}

	@Override
	public int userIdCheck(UserVO param) {
		int count = userMapper.userIdCheck(param);
		log.debug("userIdCheck count : {}", count);
		return count;
	}

	@Override
	public int userPasswordCheck(UserVO param) {
		int count = userMapper.userPasswordCheck(param);
		log.debug("userPasswordCheck count : {}", count);
		return count;
	}


	@Override
	public String certificationNumber() {
		
		// 1. 5자리 랜덤 숫자(10000 ~ 99999) 생성
        int fiveDigitNumber = 10000 + (int)(Math.random() * 90000); // 10000부터 89999 범위에 10000 더함

        // 2. 숫자를 문자열로 변환
        String fiveDigitString = String.valueOf(fiveDigitNumber); //

        // 3. 문자열에서 첫 번째 문자 제거 (인덱스 1부터 끝까지 자르기)
        String fourDigitString = fiveDigitString.substring(1); //

        // 결과 출력
        System.out.println("원본 5자리 숫자: " + fiveDigitNumber);
        System.out.println("제거 후 4자리 문자열: " + fourDigitString);

		return fourDigitString;
	}
	

}
