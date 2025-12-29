package com.project.doc5.goods.domain;

import java.util.List;

public class OptionTypeVO {

	private int    seq              ;
	private int    goodsNo         ;
	private String goodsOptionName;
	private String goodsType       ;
	private int    listSort        ;
	private String hiddenFl        ;
	
	private List<GoodsOptionVO> goodsOptionVO;
	
	public OptionTypeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OptionTypeVO(int seq, int goodsNo, String goodsOptionName, String goodsType, int listSort, String hiddenFl,
			List<GoodsOptionVO> goodsOptionVO) {
		super();
		this.seq = seq;
		this.goodsNo = goodsNo;
		this.goodsOptionName = goodsOptionName;
		this.goodsType = goodsType;
		this.listSort = listSort;
		this.hiddenFl = hiddenFl;
		this.goodsOptionVO = goodsOptionVO;
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

	public String getGoodsOptionName() {
		return goodsOptionName;
	}

	public void setGoodsOptionName(String goodsOptionName) {
		this.goodsOptionName = goodsOptionName;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public int getListSort() {
		return listSort;
	}

	public void setListSort(int listSort) {
		this.listSort = listSort;
	}

	public String getHiddenFl() {
		return hiddenFl;
	}

	public void setHiddenFl(String hiddenFl) {
		this.hiddenFl = hiddenFl;
	}

	public List<GoodsOptionVO> getGoodsOptionVO() {
		return goodsOptionVO;
	}

	public void setGoodsOptionVO(List<GoodsOptionVO> goodsOptionVO) {
		this.goodsOptionVO = goodsOptionVO;
	}

	@Override
	public String toString() {
		return "OptionTypeVO [seq=" + seq + ", goodsNo=" + goodsNo + ", goodsOptionName=" + goodsOptionName
				+ ", goodsType=" + goodsType + ", listSort=" + listSort + ", hiddenFl=" + hiddenFl + ", goodsOptionVO="
				+ goodsOptionVO + "]";
	}

	
	
	}
