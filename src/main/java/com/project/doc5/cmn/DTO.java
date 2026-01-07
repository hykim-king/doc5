package com.project.doc5.cmn;

public class DTO {
	private int no;// 글번호
	private int totalCnt;// 총 글수

	private int pageSize;// 페이지 사이즈
	private int pageNo;// 페이지 번호

	private String searchDiv;//검색구분
	private String searchWord;//검색어
	
	private int startPage; // 시작 페이지
	private int endPage; //페이지 사이즈
	
	private boolean prev; //이전 존재 유무
	private boolean next; // 다음 존재 유무
	
	public DTO() {
		super();
	}

	public DTO(int pageNo, int pageSize, int totalCnt) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCnt = totalCnt;
		
		int blockSize = 10; //페이지 묶음 크기
		
		this.endPage = (int)Math.ceil(pageNo / (double)blockSize) * blockSize;
		
		this.startPage = endPage - blockSize + 1;
		
		//실제 페이지 번호
		int realEnd = (int) Math.ceil(totalCnt / (double)pageSize);
		
		if(endPage > realEnd) {
			endPage = realEnd;
		}
		
		//이전 존재 유무
		this.prev = startPage > 1;
		
		this.next = endPage < realEnd;
		
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
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

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "DTO [no=" + no + ", totalCnt=" + totalCnt + ", pageSize=" + pageSize + ", pageNo=" + pageNo
				+ ", searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + "]";
	}

	
	
	





}
