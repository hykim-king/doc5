package com.project.doc5.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doc5.cmn.MenuVO;
import com.project.doc5.mapper.MenuMapper;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuMapper menuMapper;
	
	@Override
	public List<MenuVO> doMenuAll() {
		
		return menuMapper.doMenuAll();
	}

}
