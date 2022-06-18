package com.ssafy.vue.dto;

public class NewsDto {
	// 뉴스제목
	private String newsTitle;
	// 뉴스 기사
	private String newsContent;
	// 뉴스 기사 링크
	private String newsLink;
	// 뉴스 출처 기업
	private String newsWriting;
	// 뉴스 등록 날짜
	private String newsDate;

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsLink() {
		return newsLink;
	}

	public void setNewsLink(String newsLink) {
		this.newsLink = newsLink;
	}

	public String getNewsWriting() {
		return newsWriting;
	}

	public void setNewsWriting(String newsWriting) {
		this.newsWriting = newsWriting;
	}

	public String getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}

	@Override
	public String toString() {
		return "NewsDto [newsTitle=" + newsTitle + ", newsContent=" + newsContent + ", newsLink=" + newsLink
				+ ", newsWriting=" + newsWriting + ", newsDate=" + newsDate + "]";
	}

}
