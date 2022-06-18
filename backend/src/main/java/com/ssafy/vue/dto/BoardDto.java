package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardDto (게시글정보)", description = "글번호, 제목, 내용, 작성아이디, 작성일, 조회수, 태그번호 가진   Domain Class")
public class BoardDto {
	@ApiModelProperty(value = "글번호")
	private int no;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성아이디")
	private String user_id;
	@ApiModelProperty(value = "작성일")
	private String registDate;
	@ApiModelProperty(value = "조회수")
	private int view;
	@ApiModelProperty(value = "태그번호")
	private String tag_no;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String use_id) {
		this.user_id = use_id;
	}
	public String getTag_no() {
		return tag_no;
	}
	public void setTag_no(String tag_no) {
		this.tag_no = tag_no;
	}
	
	
}
