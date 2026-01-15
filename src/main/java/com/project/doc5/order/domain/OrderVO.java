package com.project.doc5.order.domain;

public class OrderVO {
    private String orderNo;
    private String branchCode;
    private String orderName;
    private int goodsTotalCnt;
    private double settleTotalPrice;
    private double goodsTotalPrice;
    private double optionTotalPrice;
    private String settleKind;
    private String orderStep;
    
	public OrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderVO(String orderNo, String branchCode, String orderName, int goodsTotalCnt, double settleTotalPrice,
			double goodsTotalPrice, double optionTotalPrice, String settleKind, String orderStep) {
		super();
		this.orderNo = orderNo;
		this.branchCode = branchCode;
		this.orderName = orderName;
		this.goodsTotalCnt = goodsTotalCnt;
		this.settleTotalPrice = settleTotalPrice;
		this.goodsTotalPrice = goodsTotalPrice;
		this.optionTotalPrice = optionTotalPrice;
		this.settleKind = settleKind;
		this.orderStep = orderStep;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public int getGoodsTotalCnt() {
		return goodsTotalCnt;
	}
	public void setGoodsTotalCnt(int goodsTotalCnt) {
		this.goodsTotalCnt = goodsTotalCnt;
	}
	public double getSettleTotalPrice() {
		return settleTotalPrice;
	}
	public void setSettleTotalPrice(double settleTotalPrice) {
		this.settleTotalPrice = settleTotalPrice;
	}
	public double getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public void setGoodsTotalPrice(double goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	public double getOptionTotalPrice() {
		return optionTotalPrice;
	}
	public void setOptionTotalPrice(double optionTotalPrice) {
		this.optionTotalPrice = optionTotalPrice;
	}
	public String getSettleKind() {
		return settleKind;
	}
	public void setSettleKind(String settleKind) {
		this.settleKind = settleKind;
	}
	public String getOrderStep() {
		return orderStep;
	}
	public void setOrderStep(String orderStep) {
		this.orderStep = orderStep;
	}
	@Override
	public String toString() {
		return "OrderVO [orderNo=" + orderNo + ", branchCode=" + branchCode + ", orderName=" + orderName
				+ ", goodsTotalCnt=" + goodsTotalCnt + ", settleTotalPrice=" + settleTotalPrice + ", goodsTotalPrice="
				+ goodsTotalPrice + ", optionTotalPrice=" + optionTotalPrice + ", settleKind=" + settleKind
				+ ", orderStep=" + orderStep + "]";
	}
    
    
    
}
