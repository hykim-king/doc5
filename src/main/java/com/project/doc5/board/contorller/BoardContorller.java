package com.project.doc5.board.contorller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.doc5.board.domain.BoardVO;
import com.project.doc5.board.service.BoardPaging;
import com.project.doc5.board.service.BoardService;
import com.project.doc5.cmn.DTO;
import com.project.doc5.cmn.MessageVO;

@Controller
@RequestMapping("/board")
public class BoardContorller {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	BoardService boardService;
	


	public BoardContorller() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│BoardContorller()         │");
		log.debug("└──────────────────────────┘");	
	}
	
	@GetMapping(value = "/boardView.do", produces = "text/plain;charset=UTF-8")
	public String boardView(@RequestParam(required = false, defaultValue = "notice") String code, @RequestParam int seq, @RequestParam(required = false, defaultValue = "1") int pageNo, Model model) {
		
		log.debug("┌──────────────────────────┐");
		log.debug("│boardView()               │");
		log.debug("└──────────────────────────┘");
		
		String viewString = "board/template/"+code+"/view";
		log.debug("1. seq : {}", seq);
		log.debug("1. pageNo : {}", pageNo);
		
		BoardVO param = new BoardVO();
		param.setSeq(seq);
		log.debug("2. param:{}",param);
		
		BoardVO outVO = boardService.upDoSelectOne(param);
		log.debug("3. param:{}",outVO);
		
		model.addAttribute("vo",outVO);
		
		model.addAttribute("code",code);
		model.addAttribute("pageNo",pageNo);
		
		return viewString;
		
	}
	
	@GetMapping(value = "/boardList.do", produces = "text/plain;charset=UTF-8")
	public String boardList(@RequestParam String code, @RequestParam(required = false, defaultValue = "1") int pageNo, Model model) {
		
		log.debug("┌──────────────────────────┐");
		log.debug("│boardList()               │");
		log.debug("└──────────────────────────┘");
		
		String viewString = "board/template/"+code+"/list";
		log.debug("1. code : {}", code);
		
		BoardVO param = new BoardVO();
		param.setCode(code);
		log.debug("2. param:{}",param);
		int pageSize = 10;  // 한 페이지당 글 수 

		int blockSize = 5; // 한번에 표시할 페이지 개수 
		DTO dto = new DTO();
		dto.setPageNo(pageNo);
		dto.setPageSize(pageSize);
		
		log.debug("dto : {}",dto);
		
		List<BoardVO> list = boardService.doRetrieve(dto);
		log.debug("3. list:{}",list);
		
		log.debug("dto : {}",dto);
		
		String pageUrl = "./boardList.do";
		//페이징 설정 
		//BoardPaging(총 카운트수, 페이징번호, 리스트수, 페이징 표기할 수, 게시판 코드번호, 페이징 이동경로);
		BoardPaging boardPages = new BoardPaging(list.get(0).getTotalCnt(), pageNo, pageSize, blockSize, code, pageUrl);
		
		String pageCode = boardPages.printPages();
		

		log.debug("pageCode : {}",pageCode);
		
		model.addAttribute("list",list);
		model.addAttribute("pageCode",pageCode);
		
		
		
		
		
		return viewString;
		
	}
	
	@GetMapping(value = "/doSelectOne.do", produces = "text/plain;charset=UTF-8")
	public String doSelectOne(@RequestParam int seq, Model model) {
		
		log.debug("┌──────────────────────────┐");
		log.debug("│doSelectOne()             │");
		log.debug("└──────────────────────────┘");
		
		String viewString = "board/board_view";
		log.debug("1. seq : {}", seq);
		
		BoardVO param = new BoardVO();
		param.setSeq(seq);
		log.debug("2. param:{}",param);
		
		BoardVO outVO = boardService.upDoSelectOne(param);
		log.debug("3. param:{}",outVO);
		
		model.addAttribute("vo",outVO);
		
		return viewString;
		
	}
	
	@PostMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(@RequestParam int seq) {
		log.debug("┌──────────────────────────┐");
		log.debug("│doDelete()                │");
		log.debug("└──────────────────────────┘");	
		
		String jsonString = "";
		log.debug("1. seq : {}", seq);
		
		BoardVO param = new BoardVO();
		param.setSeq(seq);
		log.debug("2. param:{}",param);
		
		int flag = boardService.doDelete(param);
		
		String message = "";
		if(1==flag) {
			message = "게시글이 삭제 되었습니다.";
		}else {
			message = "삭제 실패!";
		}
		
		jsonString = new Gson().toJson(new MessageVO(flag, message,0));
		log.debug("3. jsonString:{}",jsonString);
		
		return jsonString;
	}
	
	//JSON -> JSON데이터
	@PostMapping(value =  "/doSave.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(BoardVO param) {
		log.debug("┌──────────────────────────┐");
		log.debug("│doSave()                  │");
		log.debug("└──────────────────────────┘");	
		
		String jsonString = "";
		
		log.debug("1.param:{}",param);
		
		int flag = boardService.doSave(param);
		log.debug("2.flag:{}",flag);

		String message = "";
		if( 1 == flag ) {
			message = param.getSubject() + "이 등록 되었습니다.";
		}else {
			message = param.getSubject() + "등록 실패 했습니다.";
		}
		
		MessageVO messageVO=new MessageVO();
		messageVO.setFlag(flag);
		messageVO.setMessage(message);
		
		log.debug("3.messageVO:{}",messageVO);
		
		jsonString = new Gson().toJson(messageVO);
		log.debug("4.jsonString:{}\n",jsonString);
		
		
		return jsonString;
		
	}
	
	
}
