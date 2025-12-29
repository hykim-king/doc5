package com.project.doc5.mypage.domain;

import java.util.List;

import com.project.doc5.cmn.DTO;

public class MypageOrderVO extends DTO {
	
	private String orderNo            ;  //주문고유번호
	private String branchCode         ;  //지점고유번호
	private String orderName          ;  //주문상품명
	private int    goodsTotalCnt      ;  //상품총수량
	private double settleTotalPrice   ;  //총주문금액
	private double goodsTotalPrice    ;  //총상품금액
	private double optionTotalPrice   ;  //총옵션금액
	private String settelKind         ;  //결제방법
	private String orderStep          ;  //주문단계
	private String regDt              ;  //작성일
	private String modDt              ;  //수정일
	private String userId			  ;  //회원아이디
	

	public MypageOrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MypageOrderVO(String orderNo, String branchCode, String orderName, int goodsTotalCnt,
			double settleTotalPrice, double goodsTotalPrice, double optionTotalPrice, String settelKind,
			String orderStep, String regDt, String modDt, String userId) {
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
		this.userId = userId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "MypageOrderVO [orderNo=" + orderNo + ", branchCode=" + branchCode + ", orderName=" + orderName
				+ ", goodsTotalCnt=" + goodsTotalCnt + ", settleTotalPrice=" + settleTotalPrice + ", goodsTotalPrice="
				+ goodsTotalPrice + ", optionTotalPrice=" + optionTotalPrice + ", settelKind=" + settelKind
				+ ", orderStep=" + orderStep + ", regDt=" + regDt + ", modDt=" + modDt + ", userId=" + userId + "]";
	}


}
