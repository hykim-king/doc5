package com.project.doc5.cart.domain;

public class CartVO {
	
	private int seq;
	private int goodsNo;
	private String branchCode;
	private int orderNo;
	private String userId;
	private String goodsName;
	private double goodsPrice;
	private int goodsCnt;
	private String tumblerFl;
	private String hotFl;
	private double hotPrice;
	private String iceFl;
	private double icePrice;
	private String cancelDt;
	private String regDt;
	private String modDt;
	
	
	public CartVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CartVO(int seq, int goodsNo, String branchCode, int orderNo, String userId, String goodsName, int goodsPrice,
			int goodsCnt, String tumblerFl, String hotFl, int hotPrice, String iceFl, int icePrice, String cancelDt,
			String regDt, String modDt) {
		super();
		this.seq = seq;
		this.goodsNo = goodsNo;
		this.branchCode = branchCode;
		this.orderNo = orderNo;
		this.userId = userId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsCnt = goodsCnt;
		this.tumblerFl = tumblerFl;
		this.hotFl = hotFl;
		this.hotPrice = hotPrice;
		this.iceFl = iceFl;
		this.icePrice = icePrice;
		this.cancelDt = cancelDt;
		this.regDt = regDt;
		this.modDt = modDt;
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
	
	public String getBranchCode() {
		return branchCode;
	}
	
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public int getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public double getGoodsPrice() {
		return goodsPrice;
	}
	
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	public int getGoodsCnt() {
		return goodsCnt;
	}
	
	public void setGoodsCnt(int goodsCnt) {
		this.goodsCnt = goodsCnt;
	}
	
	public String getTumblerFl() {
		return tumblerFl;
	}
	
	public void setTumblerFl(String tumblerFl) {
		this.tumblerFl = tumblerFl;
	}
	
	public String getHotFl() {
		return hotFl;
	}
	
	public void setHotFl(String hotFl) {
		this.hotFl = hotFl;
	}
	
	public double getHotPrice() {
		return hotPrice;
	}
	
	public void setHotPrice(int hotPrice) {
		this.hotPrice = hotPrice;
	}
	
	public String getIceFl() {
		return iceFl;
	}
	
	public void setIceFl(String iceFl) {
		this.iceFl = iceFl;
	}
	
	public double getIcePrice() {
		return icePrice;
	}
	
	public void setIcePrice(int icePrice) {
		this.icePrice = icePrice;
	}
	
	public String getCancelDt() {
		return cancelDt;
	}
	
	public void setCancelDt(String cancelDt) {
		this.cancelDt = cancelDt;
	}
	
	public String getRegDt() {
		return regDt;
	}
	
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	
	public String getModDt() {
		return modDt;
	}
	
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	
	@Override
	public String toString() {
		return "CartVO [seq=" + seq + ", goodsNo=" + goodsNo + ", branchCode=" + branchCode + ", orderNo=" + orderNo
				+ ", userId=" + userId + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice + ", goodsCnt="
				+ goodsCnt + ", tumblerFl=" + tumblerFl + ", hotFl=" + hotFl + ", hotPrice=" + hotPrice + ", iceFl="
				+ iceFl + ", icePrice=" + icePrice + ", cancelDt=" + cancelDt + ", regDt=" + regDt + ", modDt=" + modDt
				+ "]";
	}

	
	
}
