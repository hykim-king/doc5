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
	public UserVO doUserLogin(UserVO param) {
		return userMapper.doUserLogin(param);
	}
	
	

}
