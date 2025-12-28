package com.project.doc5.mypage.domain;

import java.util.List;

import com.project.doc5.cmn.DTO;

public class MypageCartVO extends DTO{

	private int seq          ;//장바구니고유번호
	private int goodsNo      ;//상품고유번호
	private String branchCode   ;//지점고유번호
	private String orderNo      ;//주문고유번호
	private String userId       ;//회원아이디
	private String goodsName    ;//상품명
	private double goodsPrice   ;//판매가
	private int    goodsCnt     ;//상품수량
	private String tumblerFl    ;//텀블러사용
	private String hotFl        ;//HOT 옵션
	private double hotPrice     ;//HOT 가격
	private String iceFl        ;//ICE 옵션
	private double icePrice     ;//ICE 가격
	private String cancelDt     ;//취소일
	private String regDt        ;//등록일
	private String modDt        ;//수정일
	
	// 옵션  컬렉션 포함 (1:N 관계)
    private List<MypageCartGoodsOptionVO> mcgList;

	public MypageCartVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MypageCartVO(int seq, int goodsNo, String branchCode, String orderNo, String userId, String goodsName,
			double goodsPrice, int goodsCnt, String tumblerFl, String hotFl, double hotPrice, String iceFl,
			double icePrice, String cancelDt, String regDt, String modDt, List<MypageCartGoodsOptionVO> mcgList) {
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
		this.mcgList = mcgList;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
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

	public void setGoodsPrice(double goodsPrice) {
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

	public void setHotPrice(double hotPrice) {
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

	public void setIcePrice(double icePrice) {
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

	public List<MypageCartGoodsOptionVO> getMcgList() {
		return mcgList;
	}

	public void setMcgList(List<MypageCartGoodsOptionVO> mcgList) {
		this.mcgList = mcgList;
	}

	@Override
	public String toString() {
		return "MypageCartVO [seq=" + seq + ", goodsNo=" + goodsNo + ", branchCode=" + branchCode + ", orderNo="
				+ orderNo + ", userId=" + userId + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsCnt=" + goodsCnt + ", tumblerFl=" + tumblerFl + ", hotFl=" + hotFl + ", hotPrice=" + hotPrice
				+ ", iceFl=" + iceFl + ", icePrice=" + icePrice + ", cancelDt=" + cancelDt + ", regDt=" + regDt
				+ ", modDt=" + modDt + ", mcgList=" + mcgList + "]";
	}

	
    
	
}
