package com.project.doc5.goods.domain;

import java.util.List;

public class GoodsInfoVO {

	private int    seq                 ;
	private int    goodsNo            ;
	private String goodsType          ;
	private int    productInfoSort   ;
	private String productInfoTitle  ;
	private String productInfoText   ;
	
	
	
	public GoodsInfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public GoodsInfoVO(int seq, int goodsNo, String goodsType, int productInfoSort, String productInfoTitle,
			String productInfoText) {
		super();
		this.seq = seq;
		this.goodsNo = goodsNo;
		this.goodsType = goodsType;
		this.productInfoSort = productInfoSort;
		this.productInfoTitle = productInfoTitle;
		this.productInfoText = productInfoText;
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



	public String getGoodsType() {
		return goodsType;
	}



	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}



	public int getProductInfoSort() {
		return productInfoSort;
	}



	public void setProductInfoSort(int productInfoSort) {
		this.productInfoSort = productInfoSort;
	}



	public String getProductInfoTitle() {
		return productInfoTitle;
	}



	public void setProductInfoTitle(String productInfoTitle) {
		this.productInfoTitle = productInfoTitle;
	}



	public String getProductInfoText() {
		return productInfoText;
	}



	public void setProductInfoText(String productInfoText) {
		this.productInfoText = productInfoText;
	}



	@Override
	public String toString() {
		return "GoodsInfoVO [seq=" + seq + ", goodsNo=" + goodsNo + ", goodsType=" + goodsType + ", productInfoSort="
				+ productInfoSort + ", productInfoTitle=" + productInfoTitle + ", productInfoText=" + productInfoText
				+ "]";
	}
	
		
	
	
	}
