package com.ssafy.vue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.NewsDto;
import com.ssafy.vue.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;

	@GetMapping
	public ResponseEntity<List<NewsDto>> getNewsDatas() throws Exception{
		return new ResponseEntity<List<NewsDto>>(newsService.getNewsDatas(), HttpStatus.OK);
	}
	
}
