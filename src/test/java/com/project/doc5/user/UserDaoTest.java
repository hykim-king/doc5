package com.project.doc5.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.doc5.cmn.DTO;
import com.project.doc5.mapper.UserMapper;
import com.project.doc5.user.domain.Grade;
import com.project.doc5.user.domain.UserVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class UserDaoTest {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	ApplicationContext  context;
	
	@Autowired
	UserMapper userMapper;
	
	UserVO user01;
	UserVO user02;
	UserVO user03;
	
	DTO dto;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──setup───────────────────│");
		log.debug("└──────────────────────────┘");	
		
		user01 = new UserVO("doc5_01@doc5.com", "doc5_010", "독오01","010-0000-0000",Grade.BASIC,"날짜 없음!","NULL","NULL");		
		user02 = new UserVO("doc5_02@doc5.com", "doc5_010", "독오02","010-0000-0000",Grade.SILVER,"날짜 없음!","NULL","NULL");
		user03 = new UserVO("doc5_03@doc5.com", "doc5_010", "독오03","010-0000-0000",Grade.GOLD,"날짜 없음!","NULL","NULL");
		
		dto = new DTO();
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌──────────────────────────┐");
		log.debug("│──tearDown────────────────│");
		log.debug("└──────────────────────────┘");			
	}

	//로그인
	//@Disabled
	@Test
	void doUserLogin() {
		//1. 전체삭제
		//2. 102명 회원 생성
		//3. 로그인 정보로 회원정보 불러오기
		//4. 회원정보 있으면 로그
		
		userMapper.deleteAll();
		int count = userMapper.getCount();
		assertEquals(0, count);	
		
		// 2.
		userMapper.saveAll();
		count = userMapper.getCount();
		assertEquals(102, count);	
		
		// 3. 
		UserVO loginUser = new UserVO();	
		loginUser.setUserId("doc5_1@doc5.com");
		loginUser.setPassword("doc5_010");
		log.debug("loginUser : {}", loginUser);
		UserVO userCheckVO=userMapper.doUserLogin(loginUser);
		log.debug("userCheckVO : {}", userCheckVO);
		
		// 4.
		log.debug("┌──────────────────────────┐");
		log.debug("│로그인 완료했습니다            │");
		log.debug("└──────────────────────────┘");
		
	}
	
	//로그아웃 
	//@Disabled
	@Test
	void doLogOut() {
		//1. 섹션 삭제
		// com.project.doc5.user.controller => doLogOut 메서드에 설정완료
		
		log.debug("┌──────────────────────────┐");
		log.debug("│doLogOut()                │");
		log.debug("└──────────────────────────┘");
		
		log.debug("로그아웃 완료했습니다.");
	}
	
	//회원 수정
	//@Disabled
	@Test
	void doUpdate() {
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체 삭제
		// 2. 단건등록(user01)
		// 2-1. 단건등록(user02)
		// 3. 단건조회(user01)
		// 3-1.단건조회후 수정
		// 3-2.update수행
		// 4. 단건조회(user01)
		// 5. 비교
		
		//1.
		userMapper.deleteAll();
		int count = userMapper.getCount();
		assertEquals(0, count);				
		
		// 2.
		int flag = userMapper.doSave(user01);
		assertEquals(1, flag);
		count = userMapper.getCount();
		assertEquals(1, count);			
		
		// 2-1.
		flag = userMapper.doSave(user02);
		assertEquals(1, flag);
		count = userMapper.getCount();
		assertEquals(2, count);		
		
		// 3. 
		UserVO outVO = userMapper.doSelectOne(user01);
		assertNotNull(outVO);	
		
		// 3-1.
		String upString = "_U";
	
		outVO.setName(outVO.getName()+upString); 		//이름
		outVO.setPassword(outVO.getPassword()+upString);//비밀번호
		outVO.setPhone("010-1111-1111");				//전화번호
		outVO.setGrade(Grade.SILVER);					//등급
		
		// 3-2.
		flag = userMapper.doUpdate(outVO);
		assertEquals(1, flag);
		
		// 4. 
		UserVO upResultVO=userMapper.doSelectOne(outVO);
		isSameUser(upResultVO, outVO);		
	}
	
	//회원가입 
	//@Disabled
	@Test
	void doSave() {
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체 삭제
		// 2. 단건등록(user01)	
		// 3. 전체건수 조회
		// 4. 실제데이터 조회
		
		
		//1.
		userMapper.deleteAll();
		
		//2.
		int flag = userMapper.doSave(user01);
		assertEquals(1, flag);
		
		//3.
		int count = userMapper.getCount();
		assertEquals(1, count);
		
		//4.
		UserVO outVO=userMapper.doSelectOne(user01);
		assertNotNull(outVO);
		
		isSameUser(outVO,user01);
		
	}
	
	//일치여부 확인
	private void isSameUser(UserVO outVO, UserVO user01) {
		assertEquals(outVO.getUserId(), user01.getUserId());
		assertEquals(outVO.getName(), user01.getName());
		assertEquals(outVO.getPassword(), user01.getPassword());
		assertEquals(outVO.getPhone(), user01.getPhone());
		assertEquals(outVO.getGrade(), user01.getGrade());
	}	

	//단건 삭제 (회원탈퇴)
	//@Disabled
	@Test
	void doDelete() {
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체삭제		
		// 2. 한건등록
		// 2.1 한건등록
		// 3. 단건삭제
		// 4. 건수비교
		
		//1.
		userMapper.deleteAll();
		int count = userMapper.getCount();
		assertEquals(0, count);		

		// 2.
		int flag = userMapper.doSave(user01);
		assertEquals(1, flag);
		count = userMapper.getCount();
		assertEquals(1, count);			
		
		// 2-1.
		flag = userMapper.doSave(user02);
		assertEquals(1, flag);
		count = userMapper.getCount();
		assertEquals(2, count);			
		
		// 3.
		flag = userMapper.doDelete(user01);
		assertEquals(1, flag);
		
		//4.
		count = userMapper.getCount();
		assertEquals(1, count);	
		
	}

	
// [관리자 예정]-------------------------------------------------------------------------	
	
	//전체 삭제
	@Disabled
	@Test
	void deleteAll() { 
		int flag = userMapper.deleteAll();
		
		log.debug("flag: "+flag);
	}
	
	

	
	//회원 페이징 리스트 , 데이터 대량 등록 102건 (페이징 포함)
	@Disabled
	@Test
	void doRetrieve() {
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체삭제
		// 2. 102건 등록
		// 3. 페이징 조회
		// 4. 결과 확인
	
		userMapper.deleteAll();
		int count = userMapper.getCount();
		assertEquals(0, count);	
		
		// 2.
		userMapper.saveAll();
		count = userMapper.getCount();
		assertEquals(102, count);	
	
		// 3.
		dto.setPageSize(10);
		dto.setPageNo(1);
		
		//검색조건
		dto.setSearchDiv("20");
		dto.setSearchWord("이상무1");
		List<UserVO> list = userMapper.doRetrieve(dto);
		
		// 4.
		assertEquals(10, list.size());	
	}
	
	
	//회원 전체 불러오기
	@Disabled
	@Test
	void getAll() {
		// 매번 동일 결과가 도출 되도록 작성.
		// 1. 전체삭제
		// 2. 한건등록
		// 2.1 한건등록
		// 2.2 한거등록
		// 3. 전체검색
		// 4. 건수조회
		
		userMapper.deleteAll();
		int count = userMapper.getCount();
		assertEquals(0, count);	
		
		// 2.
		int flag = userMapper.doSave(user01);
		assertEquals(1, flag);
		count = userMapper.getCount();
		assertEquals(1, count);			
		
		// 2-1.
		flag = userMapper.doSave(user02);
		assertEquals(1, flag);
		count = userMapper.getCount();
		assertEquals(2, count);	
		
		// 2-2.
		flag = userMapper.doSave(user03);
		assertEquals(1, flag);
		count = userMapper.getCount();
		assertEquals(3, count);		
		
		//3.
		List<UserVO> list = userMapper.getAll();
		
		//4.
		assertEquals(3, list.size());
		
	}
	

	
	
	@Disabled
	@Test
	void beans() {
		log.debug("┌──────────────────────────┐");
		log.debug("│─beans()                  │");
		log.debug("└──────────────────────────┘");	
		
		log.debug("context:"+context);
		log.debug("userMapper:"+userMapper);
		assertNotNull(context);
		assertNotNull(userMapper);
	}

}
