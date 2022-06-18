package com.ssafy.vue.dto;

public class LocalLikeDto {
	int no;
	String user_id;
	String registDate;
	String dongCode;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	
	@Override
	public String toString() {
		return "LocalLikeDto [no=" + no + ", user_id=" + user_id + ", registDate=" + registDate + ", dongCode="
				+ dongCode + "]";
	}
	
	
}
