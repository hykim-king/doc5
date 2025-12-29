package com.project.doc5.goods.domain;

import java.util.List;

public class GoodsVO {

	private int    goodsNo             ;
	private String goodsName        ;
	private String goodsEngName    ;
	private double goodsPrice       ;
	private String hotFl            ;
	private double hotPrice         ;
	private String iceFl            ;
	private double icePrice         ;
	private String tumblerFl        ;
	private String shortDescription ;
	private String goodsContents    ;
	private String newFl            ;
	private String bestFl           ;
	private String alleojiInfo      ;
	private int    goodsView        ;
	private String hiddenFl         ;
	private String regDt            ;
	private String regId            ;
	private String modDt            ;
	private String modId            ;
	
	private List<OptionTypeVO> optionTypeVO;
	private List<CategoryJoinVO> categoryJoinVO;
	private List<GoodsInfoVO> goodsInfoVO;
	
	
	
	public GoodsVO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public GoodsVO(int goodsNo, String goodsName, String goodsEngName, double goodsPrice, String hotFl, double hotPrice,
			String iceFl, double icePrice, String tumblerFl, String shortDescription, String goodsContents,
			String newFl, String bestFl, String alleojiInfo, int goodsView, String hiddenFl, String regDt, String regId,
			String modDt, String modId, List<OptionTypeVO> optionTypeVO, List<CategoryJoinVO> categoryJoinVO,
			List<GoodsInfoVO> goodsInfoVO) {
		super();
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.goodsEngName = goodsEngName;
		this.goodsPrice = goodsPrice;
		this.hotFl = hotFl;
		this.hotPrice = hotPrice;
		this.iceFl = iceFl;
		this.icePrice = icePrice;
		this.tumblerFl = tumblerFl;
		this.shortDescription = shortDescription;
		this.goodsContents = goodsContents;
		this.newFl = newFl;
		this.bestFl = bestFl;
		this.alleojiInfo = alleojiInfo;
		this.goodsView = goodsView;
		this.hiddenFl = hiddenFl;
		this.regDt = regDt;
		this.regId = regId;
		this.modDt = modDt;
		this.modId = modId;
		this.optionTypeVO = optionTypeVO;
		this.categoryJoinVO = categoryJoinVO;
		this.goodsInfoVO = goodsInfoVO;
	}



	public int getGoodsNo() {
		return goodsNo;
	}



	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}



	public String getGoodsName() {
		return goodsName;
	}



	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}



	public String getGoodsEngName() {
		return goodsEngName;
	}



	public void setGoodsEngName(String goodsEngName) {
		this.goodsEngName = goodsEngName;
	}



	public double getGoodsPrice() {
		return goodsPrice;
	}



	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
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



	public String getTumblerFl() {
		return tumblerFl;
	}



	public void setTumblerFl(String tumblerFl) {
		this.tumblerFl = tumblerFl;
	}



	public String getShortDescription() {
		return shortDescription;
	}



	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}



	public String getGoodsContents() {
		return goodsContents;
	}



	public void setGoodsContents(String goodsContents) {
		this.goodsContents = goodsContents;
	}



	public String getNewFl() {
		return newFl;
	}



	public void setNewFl(String newFl) {
		this.newFl = newFl;
	}



	public String getBestFl() {
		return bestFl;
	}



	public void setBestFl(String bestFl) {
		this.bestFl = bestFl;
	}



	public String getAlleojiInfo() {
		return alleojiInfo;
	}



	public void setAlleojiInfo(String alleojiInfo) {
		this.alleojiInfo = alleojiInfo;
	}



	public int getGoodsView() {
		return goodsView;
	}



	public void setGoodsView(int goodsView) {
		this.goodsView = goodsView;
	}



	public String getHiddenFl() {
		return hiddenFl;
	}



	public void setHiddenFl(String hiddenFl) {
		this.hiddenFl = hiddenFl;
	}



	public String getRegDt() {
		return regDt;
	}



	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}



	public String getRegId() {
		return regId;
	}



	public void setRegId(String regId) {
		this.regId = regId;
	}



	public String getModDt() {
		return modDt;
	}



	public void setModDt(String modDt) {
		this.modDt = modDt;
	}



	public String getModId() {
		return modId;
	}



	public void setModId(String modId) {
		this.modId = modId;
	}



	public List<OptionTypeVO> getOptionTypeVO() {
		return optionTypeVO;
	}



	public void setOptionTypeVO(List<OptionTypeVO> optionTypeVO) {
		this.optionTypeVO = optionTypeVO;
	}



	public List<CategoryJoinVO> getCategoryJoinVO() {
		return categoryJoinVO;
	}



	public void setCategoryJoinVO(List<CategoryJoinVO> categoryJoinVO) {
		this.categoryJoinVO = categoryJoinVO;
	}



	public List<GoodsInfoVO> getGoodsInfoVO() {
		return goodsInfoVO;
	}



	public void setGoodsInfoVO(List<GoodsInfoVO> goodsInfoVO) {
		this.goodsInfoVO = goodsInfoVO;
	}



	@Override
	public String toString() {
		return "GoodsVO [goodsNo=" + goodsNo + ", goodsName=" + goodsName + ", goodsEngName=" + goodsEngName
				+ ", goodsPrice=" + goodsPrice + ", hotFl=" + hotFl + ", hotPrice=" + hotPrice + ", iceFl=" + iceFl
				+ ", icePrice=" + icePrice + ", tumblerFl=" + tumblerFl + ", shortDescription=" + shortDescription
				+ ", goodsContents=" + goodsContents + ", newFl=" + newFl + ", bestFl=" + bestFl + ", alleojiInfo="
				+ alleojiInfo + ", goodsView=" + goodsView + ", hiddenFl=" + hiddenFl + ", regDt=" + regDt + ", regId="
				+ regId + ", modDt=" + modDt + ", modId=" + modId + ", optionTypeVO=" + optionTypeVO
				+ ", categoryJoinVO=" + categoryJoinVO + ", goodsInfoVO=" + goodsInfoVO + "]";
	}


	
	}
