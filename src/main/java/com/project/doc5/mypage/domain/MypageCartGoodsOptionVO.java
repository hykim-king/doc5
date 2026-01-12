package com.project.doc5.mypage.domain;

public class MypageCartGoodsOptionVO {
	
	private int    seq               ;//장바구니옵션고유번호
	private int    cSeq             ;//장바구니 고유번호
	private double goodsNo          ;//상품고유번호
	private String goodsType        ;//상품타입
	private String optionName       ;//옵션명
	private double optionPrice      ;//옵션가격
	
	public MypageCartGoodsOptionVO() {
		super();
		// TODO Auto-generated constructor stub
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

	public double getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(double goodsNo) {
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

	public double getOptionPrice() {
		return optionPrice;
	}

	public void setOptionPrice(double optionPrice) {
		this.optionPrice = optionPrice;
	}

	@Override
	public String toString() {
		return "MypageCartGoodsOptionVO [seq=" + seq + ", cSeq=" + cSeq + ", goodsNo=" + goodsNo + ", goodsType="
				+ goodsType + ", optionName=" + optionName + ", optionPrice=" + optionPrice + "]";
	}

	
	
	
	
	
	
}
