package com.project.doc5.board.service;

public class BoardPaging {

	private int totalPosts;
	private int currentPage;
	private int postsPerPage;
	private int displayPageNum;
	private String pageUrl;
	private String code;

	public BoardPaging(int totalPosts, int currentPage, int postsPerPage, int displayPageNum, String code, String pageUrl) {
		super();
		this.totalPosts = totalPosts;
		this.currentPage = currentPage;
		this.postsPerPage = postsPerPage;
		this.displayPageNum = displayPageNum;
		this.code = code;
		this.pageUrl = pageUrl;
	}

	public int getTotalPosts() {
		return totalPosts;
	}

	public void setTotalPosts(int totalPosts) {
		this.totalPosts = totalPosts;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPostsPerPage() {
		return postsPerPage;
	}

	public void setPostsPerPage(int postsPerPage) {
		this.postsPerPage = postsPerPage;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "BoardPages [totalPosts=" + totalPosts + ", currentPage=" + currentPage + ", postsPerPage="
				+ postsPerPage + ", displayPageNum=" + displayPageNum + ", code=" + code + "]";
	}

	private int totalPages(int totalPosts, int postsPerPage) {
        return ((totalPosts - 1) / postsPerPage) + 1;
    }

    private int endPage(int currentPage, int displayPageNum, int totalPages) {
        int endPage = (((currentPage-1)/displayPageNum)+1) * displayPageNum;

        if(totalPages < endPage)
            endPage = totalPages;

        return endPage;
    }

    private int startPage(int currentPage, int displayPageNum) {
        return ((currentPage - 1) / displayPageNum) * displayPageNum + 1;
    }

    private boolean hasPrev(int startPage) {
        return (startPage == 1) ? false : true;
    }

    private boolean hasNext(int endPage, int totalPages) {
        return (endPage == totalPages) ? false : true;
    }
    
    

    public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String printPages() {
        int totalPages = totalPages(this.getTotalPosts(), this.getPostsPerPage());
        int startPage = startPage(this.getCurrentPage(), this.getDisplayPageNum());
        int endPage = endPage(this.getCurrentPage(), this.getDisplayPageNum(), totalPages);
        boolean hasPrev = hasPrev(startPage);
        boolean hasNext = hasNext(endPage, totalPages);
       

        String pageCode;
        String nowPageCheck;
        pageCode = "<ul>";
        		
        if(hasPrev) {
        	pageCode += "<li><a href='"+this.pageUrl+"?code="+this.code+"&pageNo=1'>처음</a></li>";
        	pageCode += "<li class='pagingPrev'><a href='"+this.pageUrl+"?code="+this.code+"&pageNo="+(startPage-1)+"'>이전</a></li>";
        }
        for (int page = startPage; page <= endPage; page++) {
        	nowPageCheck =  this.currentPage == page ? "class='on'" : "";
        	pageCode += "<li "+nowPageCheck+"><a href='"+this.pageUrl+"?code="+this.code+"&pageNo="+page+"'>"+page+"</a></li>";
        }
        if(hasNext){
        	pageCode += "<li class='pagingNext'><a href='"+this.pageUrl+"?code="+this.code+"&pageNo="+(endPage+1)+"'>다음</a></li>";
        	pageCode += "<li><a href='"+this.pageUrl+"?code="+this.code+"&pageNo="+totalPages+"'>마지막</a></li>";
        }
        pageCode += "<ul>";
        
        return pageCode;
    }
    
    
	
	
	
	

}
