package com.project.doc5.user.domain;

import java.io.Serializable;

import com.project.doc5.cmn.DTO;

public class UserVO extends DTO implements Serializable{

	private String userId; // 사용자ID
	private String password;// 비밀번호
	private String name; // 이름
	private String phone; // 전화번호
	private Grade grade;// 등급
	private String regDt; // 등록일
	private String modDt; // 수정일
	private String privacyDt; // 개인정보취급방침

	// 전역변수
	// Default 생성자
	// 인자있는 생성자
	// get/setters
	// toString()

	public UserVO() {
		super();
	}

	public UserVO(String userId, String password, String name, String phone, Grade grade, String regDt, String modDt,
			String privacyDt) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.grade = grade;
		this.regDt = regDt;
		this.modDt = modDt;
		this.privacyDt = privacyDt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
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

	public String getPrivacyDt() {
		return privacyDt;
	}

	public void setPrivacyDt(String privacyDt) {
		this.privacyDt = privacyDt;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", grade=" + grade + ", regDt=" + regDt + ", modDt=" + modDt + ", privacyDt=" + privacyDt + "]";
	}


	



}
