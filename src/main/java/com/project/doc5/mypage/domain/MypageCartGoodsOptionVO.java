package com.project.doc5.mypage.domain;

public class MypageCartGoodsOptionVO {
	
	private int    seq               ;//장바구니옵션고유번호
	private int    c_seq             ;//장바구니 고유번호
	private double goods_no          ;//상품고유번호
	private String goods_type        ;//상품타입
	private String option_name       ;//옵션명
	private double option_price      ;//옵션가격
	
	public MypageCartGoodsOptionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MypageCartGoodsOptionVO(int seq, int c_seq, double goods_no, String goods_type, String option_name,
			double option_price) {
		super();
		this.seq = seq;
		this.c_seq = c_seq;
		this.goods_no = goods_no;
		this.goods_type = goods_type;
		this.option_name = option_name;
		this.option_price = option_price;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getC_seq() {
		return c_seq;
	}

	public void setC_seq(int c_seq) {
		this.c_seq = c_seq;
	}

	public double getGoods_no() {
		return goods_no;
	}

	public void setGoods_no(double goods_no) {
		this.goods_no = goods_no;
	}

	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	public String getOption_name() {
		return option_name;
	}

	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}

	public double getOption_price() {
		return option_price;
	}

	public void setOption_price(double option_price) {
		this.option_price = option_price;
	}

	@Override
	public String toString() {
		return "MypageCartGoodsOptionVO [seq=" + seq + ", c_seq=" + c_seq + ", goods_no=" + goods_no + ", goods_type="
				+ goods_type + ", option_name=" + option_name + ", option_price=" + option_price + "]";
	}
	
	
	
	
	
}
