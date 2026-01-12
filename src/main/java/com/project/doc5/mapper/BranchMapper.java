package com.project.doc5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doc5.branch.domain.BranchVO;
@Mapper
public interface BranchMapper {
	List<BranchVO> searchCoordinate();
}
