package com.project.doc5.user;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.doc5.cmn.MessageVO;

class Log4j2Text {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void jsonTest() {
		int flag = 0;
		String message = "등록 되었습니다.";
		
		
		
		
		
		MessageVO messageVO=new MessageVO();
		messageVO.setFlag(flag);
		messageVO.setMessage(message);
		
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
