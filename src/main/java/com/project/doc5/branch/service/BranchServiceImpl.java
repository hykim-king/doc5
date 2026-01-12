package com.project.doc5.branch.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doc5.branch.domain.BranchVO;
import com.project.doc5.mapper.BranchMapper;
@Service
public class BranchServiceImpl implements BranchService {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	BranchMapper branchMapper;  
	
	
	
	
	public BranchServiceImpl() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│BranchServiceImpl         │");
		log.debug("└──────────────────────────┘");	
	}




	@Override
	public List<BranchVO> searchCoordinate() {
		
		return branchMapper.searchCoordinate();
	}

}
