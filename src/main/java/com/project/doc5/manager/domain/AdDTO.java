package com.project.doc5.manager.domain;

public class AdDTO {
	private int pageSize;      // 페이지 사이즈 (쿼리 3-2 사용)
    private int pageNo;        // 페이지 번호 (쿼리 3-2 사용)
    private String searchWord;  // 회원 검색어 (쿼리 2.1 사용)
    
	private int startNo; // 시작 페이지 번호( 1,11...)
	private int endNo; // 끝 페이지 번호 (10,20...)

	private boolean pre; // 이전 존재 유무
	private boolean next;// 다음 존재 유무
    
	
    
    private String branchCode;  // 총 매출 (1), 미완료 주문 (3-2) 조회 시 사용
    private String userId;      // 회원별 주문 내역 (3-1) 조회 시 사용
	public AdDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	public boolean isPre() {
		return pre;
	}
	public void setPre(boolean pre) {
		this.pre = pre;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "AdDTO [pageSize=" + pageSize + ", pageNo=" + pageNo + ", searchWord=" + searchWord + ", startNo="
				+ startNo + ", endNo=" + endNo + ", pre=" + pre + ", next=" + next + ", branchCode=" + branchCode
				+ ", userId=" + userId + "]";
	}
	
}
