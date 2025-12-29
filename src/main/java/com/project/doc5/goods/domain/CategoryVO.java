package com.project.doc5.goods.domain;

import java.util.List;

public class CategoryVO {

	private String code             ;
	private String categoryName    ;
	private String hiddenFl        ;
	
	
	
	
	
	public CategoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}





	public CategoryVO(String code, String categoryName, String hiddenFl) {
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
		return "CategoryVO [code=" + code + ", categoryName=" + categoryName + ", hiddenFl=" + hiddenFl + "]";
	}
		
	
	}
