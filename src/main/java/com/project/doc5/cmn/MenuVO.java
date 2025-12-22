package com.project.doc5.cmn;

public class MenuVO {
	private String code; // 카테고리 코드
	private String categoryName;// 카테고리명
	private String hiddenFl;// 숨김처리
	
	public MenuVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuVO(String code, String categoryName, String hiddenFl) {
		super();
		this.code = code;
		this.categoryName = categoryName;
		this.hiddenFl = hiddenFl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getHiddenFl() {
		return hiddenFl;
	}

	public void setHiddenFl(String hiddenFl) {
		this.hiddenFl = hiddenFl;
	}

	@Override
	public String toString() {
		return "MenuVO [code=" + code + ", categoryName=" + categoryName + ", hiddenFl=" + hiddenFl + "]";
	}
	
	
	
	
}
