package com.project.doc5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doc5.cmn.MenuVO;

@Mapper
public interface MenuMapper {
	
	List<MenuVO> doMenuAll();
	
}
