package com.project.doc5.cmn;

public class MessageVO extends DTO {

	private int flag; //상태코드
	private String message;//메세지
	private int seq;
	private String orderNo;
	
	public MessageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageVO(int flag, String message, int seq, String orderNo) {
		super();
		this.flag = flag;
		this.message = message;
		this.seq = seq;
		this.orderNo = orderNo;
	}


	
	
	public MessageVO(int flag, String message, int seq) {
		super();
		this.flag = flag;
		this.message = message;
		this.seq = seq;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "MessageVO [flag=" + flag + ", message=" + message + ", seq=" + seq + ", orderNo=" + orderNo + "]";
	}
	
	
	
	
	
}
