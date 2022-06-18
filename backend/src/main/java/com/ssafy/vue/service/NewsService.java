package com.ssafy.vue.service;

import java.util.List;

import javax.annotation.PostConstruct;

import com.ssafy.vue.dto.NewsDto;

public interface NewsService {
	
	@PostConstruct
	public List<NewsDto> getNewsDatas() throws Exception;
}
