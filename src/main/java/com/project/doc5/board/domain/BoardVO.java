package com.project.doc5.board.domain;

import com.project.doc5.cmn.DTO;

public class BoardVO extends DTO {
	
	private int seq       ;    //게시판고유번호
	private String subject   ;    //제목
	private String contents  ;    //내용
	private int viewCnt  ;    //뷰카운트
	private String regDt    ;    //작성일
	private String regId    ;    //작성자
	private String modDt    ;    //수정일
	private String modId    ;    //수정자
	
	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardVO(int seq, String subject, String contents, int viewCnt, String regDt, String regId, String modDt,
			String modId) {
		super();
		this.seq = seq;
		this.subject = subject;
		this.contents = contents;
		this.viewCnt = viewCnt;
		this.regDt = regDt;
		this.regId = regId;
		this.modDt = modDt;
		this.modId = modId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", subject=" + subject + ", contents=" + contents + ", viewCnt=" + viewCnt
				+ ", regDt=" + regDt + ", regId=" + regId + ", modDt=" + modDt + ", modId=" + modId + "]";
	}

	
	
	
	
	
}
