package com.project.doc5.goods.domain;

import java.util.List;

public class CategoryJoinVO {

	private int    seq         ;
	private int    goodsNo    ;
	private String code        ;
	private String hiddenFl   ;

	private CategoryVO categoryVO;

	public CategoryJoinVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryJoinVO(int seq, int goodsNo, String code, String hiddenFl, CategoryVO categoryVO) {
		super();
		this.seq = seq;
		this.goodsNo = goodsNo;
		this.code = code;
		this.hiddenFl = hiddenFl;
		this.categoryVO = categoryVO;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHiddenFl() {
		return hiddenFl;
	}

	public void setHiddenFl(String hiddenFl) {
		this.hiddenFl = hiddenFl;
	}

	public CategoryVO getCategoryVO() {
		return categoryVO;
	}

	public void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}

	@Override
	public String toString() {
		return "CategoryJoinVO [seq=" + seq + ", goodsNo=" + goodsNo + ", code=" + code + ", hiddenFl=" + hiddenFl
				+ ", categoryVO=" + categoryVO + "]";
	}

		
	
}
