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


	
	

}
