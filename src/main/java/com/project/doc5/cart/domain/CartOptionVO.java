package com.project.doc5.cart.domain;

public class CartOptionVO {
	
	private int seq;
	private int cSeq;
	private int goodsNo;
	private String goodsType;
	private String optionName;
	private int optionPrice;
	
	public CartOptionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartOptionVO(int seq, int cSeq, int goodsNo, String goodsType, String optionName, int optionPrice) {
		super();
		this.seq = seq;
		this.cSeq = cSeq;
		this.goodsNo = goodsNo;
		this.goodsType = goodsType;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getcSeq() {
		return cSeq;
	}

	public void setcSeq(int cSeq) {
		this.cSeq = cSeq;
	}

	public int getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public int getOptionPrice() {
		return optionPrice;
	}

	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}

	@Override
	public String toString() {
		return "CartOptionVO [seq=" + seq + ", cSeq=" + cSeq + ", goodsNo=" + goodsNo + ", goodsType=" + goodsType
				+ ", optionName=" + optionName + ", optionPrice=" + optionPrice + "]";
	}
	
	
	
	
}
