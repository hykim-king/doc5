package com.project.doc5.goods.domain;


public class GoodsOptionVO {

	private int    seq          ;
	private int    otSeq       ;
	private String optionStep  ;
	private String optionName  ;
	private double optionPrice ;
	private String optionHidden;

	public GoodsOptionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsOptionVO(int seq, int otSeq, String optionStep, String optionName, double optionPrice,
			String optionHidden) {
		super();
		this.seq = seq;
		this.otSeq = otSeq;
		this.optionStep = optionStep;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
		this.optionHidden = optionHidden;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getOtSeq() {
		return otSeq;
	}

	public void setOtSeq(int otSeq) {
		this.otSeq = otSeq;
	}

	public String getOptionStep() {
		return optionStep;
	}

	public void setOptionStep(String optionStep) {
		this.optionStep = optionStep;
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

	public String getOptionHidden() {
		return optionHidden;
	}

	public void setOptionHidden(String optionHidden) {
		this.optionHidden = optionHidden;
	}

	@Override
	public String toString() {
		return "GoodsOptionVO [seq=" + seq + ", otSeq=" + otSeq + ", optionStep=" + optionStep + ", optionName="
				+ optionName + ", optionPrice=" + optionPrice + ", optionHidden=" + optionHidden + "]";
	}
	
}
