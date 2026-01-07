package com.project.doc5.goods.domain;

public class GoodsSearchKeywordVO {
	
	private int seq        ;//고유번호
	private String userId  ;//아이디
	private String keyword ;//키워드
	private String hiddenFl;//숨김처
	private String regDt   ;//등록일
	private String tmpUserId ;//섹션 아이
	
	public GoodsSearchKeywordVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsSearchKeywordVO(int seq, String userId, String keyword, String hiddenFl, String regDt,
			String tmpUserId) {
		super();
		this.seq = seq;
		this.userId = userId;
		this.keyword = keyword;
		this.hiddenFl = hiddenFl;
		this.regDt = regDt;
		this.tmpUserId = tmpUserId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getHiddenFl() {
		return hiddenFl;
	}

	public void setHiddenFl(String hiddenFl) {
		this.hiddenFl = hiddenFl;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getTmpUserId() {
		return tmpUserId;
	}

	public void setTmpUserId(String tmpUserId) {
		this.tmpUserId = tmpUserId;
	}

	@Override
	public String toString() {
		return "GoodsSearchKeywordVO [seq=" + seq + ", userId=" + userId + ", keyword=" + keyword + ", hiddenFl="
				+ hiddenFl + ", regDt=" + regDt + ", tmpUserId=" + tmpUserId + "]";
	}

	

	
	
	
	
	
}
