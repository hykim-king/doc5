package com.project.doc5.manager.domain;

public class AdOrderVO {
	private String orderNo; 
    private String branchCode; 
    private String orderName; 
    private int goodsTotalCnt; 
    private double settleTotalPrice; 
    private double goodsTotalPrice; 
    private double optionTotalPrice; 
    private String settelKind; 
    private String orderStep; 
    private String regDt; 
    private String modDt; 
   
    private String goodsName;
    private String tumblerFl;
    private String hotFl;
    private String iceFl;
    
    
    
    // 쿼리 1번 결과를 담기 위한 통계 필드
    private Double totalSales; 
    private int totalOrderCount; 
    
	public AdOrderVO() {
		super();
	}

	public AdOrderVO(String orderNo, String branchCode, String orderName, int goodsTotalCnt, double settleTotalPrice,
			double goodsTotalPrice, double optionTotalPrice, String settelKind, String orderStep, String regDt,
			String modDt, String goodsName, String tumblerFl, String hotFl, String iceFl, Double totalSales,
			int totalOrderCount) {
		super();
		this.orderNo = orderNo;
		this.branchCode = branchCode;
		this.orderName = orderName;
		this.goodsTotalCnt = goodsTotalCnt;
		this.settleTotalPrice = settleTotalPrice;
		this.goodsTotalPrice = goodsTotalPrice;
		this.optionTotalPrice = optionTotalPrice;
		this.settelKind = settelKind;
		this.orderStep = orderStep;
		this.regDt = regDt;
		this.modDt = modDt;
		this.goodsName = goodsName;
		this.tumblerFl = tumblerFl;
		this.hotFl = hotFl;
		this.iceFl = iceFl;
		this.totalSales = totalSales;
		this.totalOrderCount = totalOrderCount;
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

	public String getSettelKind() {
		return settelKind;
	}

	public void setSettelKind(String settelKind) {
		this.settelKind = settelKind;
	}

	public String getOrderStep() {
		return orderStep;
	}

	public void setOrderStep(String orderStep) {
		this.orderStep = orderStep;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getIceFl() {
		return iceFl;
	}

	public void setIceFl(String iceFl) {
		this.iceFl = iceFl;
	}

	public Double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}

	public int getTotalOrderCount() {
		return totalOrderCount;
	}

	public void setTotalOrderCount(int totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}

	@Override
	public String toString() {
		return "AdOrderVO [orderNo=" + orderNo + ", branchCode=" + branchCode + ", orderName=" + orderName
				+ ", goodsTotalCnt=" + goodsTotalCnt + ", settleTotalPrice=" + settleTotalPrice + ", goodsTotalPrice="
				+ goodsTotalPrice + ", optionTotalPrice=" + optionTotalPrice + ", settelKind=" + settelKind
				+ ", orderStep=" + orderStep + ", regDt=" + regDt + ", modDt=" + modDt + ", goodsName=" + goodsName
				+ ", tumblerFl=" + tumblerFl + ", hotFl=" + hotFl + ", iceFl=" + iceFl + ", totalSales=" + totalSales
				+ ", totalOrderCount=" + totalOrderCount + "]";
	}

	


}