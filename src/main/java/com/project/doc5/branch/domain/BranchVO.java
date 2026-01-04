package com.project.doc5.branch.domain;

public class BranchVO {
	
	private String branchCode     ;//지점코드
	private String branchName     ;//지점명
	private double lat            ;//위도
	private double lng            ;//경도
	private String zoneCode       ;//우편번호
	private String address1       ;//주소
	private String address2       ;//상세주소
	private String tel            ;//전화번호
	private String hiddenFl       ;//사용여부
	
	public BranchVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BranchVO(String branchCode, String branchName, double lat, double lng, String zoneCode, String address1,
			String address2, String tel, String hiddenFl) {
		super();
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.lat = lat;
		this.lng = lng;
		this.zoneCode = zoneCode;
		this.address1 = address1;
		this.address2 = address2;
		this.tel = tel;
		this.hiddenFl = hiddenFl;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getHiddenFl() {
		return hiddenFl;
	}

	public void setHiddenFl(String hiddenFl) {
		this.hiddenFl = hiddenFl;
	}

	@Override
	public String toString() {
		return "BranchVO [branchCode=" + branchCode + ", branchName=" + branchName + ", lat=" + lat + ", lng=" + lng
				+ ", zoneCode=" + zoneCode + ", address1=" + address1 + ", address2=" + address2 + ", tel=" + tel
				+ ", hiddenFl=" + hiddenFl + "]";
	}

}
