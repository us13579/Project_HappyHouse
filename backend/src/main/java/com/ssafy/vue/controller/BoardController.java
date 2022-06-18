package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.BoardDto;
import com.ssafy.vue.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin("*")
@Api("BoardController V1")
@RestController
@RequestMapping("/board")
public class BoardController {

	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BoardService boardService;

	// 모든 게시글 반환
	// Swagger
	@ApiOperation(value = "모든 게시글의 정보를 반환", response = List.class)
	@GetMapping
	public ResponseEntity<List<BoardDto>> listBoard() throws Exception {
		logger.debug("listBoard 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.listBoard(), HttpStatus.OK);
	}

	

		// 게시 글 작성
		@ApiOperation(value = "게시 글 작성, 작성을 성공하면 'success'를  실패하면 'fail'을 반환", response = String.class)
		@PostMapping
		public ResponseEntity<String> insertBoard(@RequestBody BoardDto boardDto) throws Exception {
			logger.debug("insertBoard 호출");
			if (boardService.insertBoard(boardDto)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		}
		
		// 글 번호에 해당하는 게시글 반환
		@ApiOperation(value = "글 번호에 해당하는 게시글 반환", response = BoardDto.class)
		@GetMapping("{no}")
		public ResponseEntity<BoardDto> selectBoard(@PathVariable int no) throws Exception {
			logger.debug("selectBoard 호출");
			return new ResponseEntity<BoardDto>(boardService.selectBoard(no), HttpStatus.OK);
		}
		
		// 게시 글 수정
		@ApiOperation(value = "게시 글 수정, 수정 성공하면 'success' 를 실패하면 'fail'을 반환", response = String.class)
		@PutMapping("{no}")
		public ResponseEntity<String> updateBoard(@RequestBody BoardDto boardDto) throws Exception{
			logger.debug("updateBoard 호출, board : " + boardDto.getTitle() + " " + boardDto.getContent() + " " + boardDto.getTag_no() + " " + boardDto.getNo());
			if(boardService.updateBoard(boardDto)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		}
		
		// 게시 글 삭제
		@ApiOperation(value = "게시 글 삭제, 삭제 성공하면 'success' 를 실패하면 'fail'을 반환", response = String.class)
		@DeleteMapping("{no}")
		public ResponseEntity<String> deleteBoard(@PathVariable int no) throws Exception{
			logger.debug("deleteBoard 호출, no : " + no);
			if(boardService.deleteBoard(no)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		}
		
		// 태그 이름 가져오기
		@ApiOperation(value="태그 이름 가져오기", response = String.class)
		@GetMapping("tag/{no}")
		public ResponseEntity<String> selectTag(@PathVariable("no") int no) throws Exception{
			logger.debug("selectTag 호출 " + no);
			return new ResponseEntity<String>(boardService.selectTag(no), HttpStatus.OK);
		}
		
		// 조회수 업데이트
		@ApiOperation(value="조회수 업데이트 하기, 업데이트 성공하면 'success' 를 실패하면 'fail' 을 반환", response = String.class)
		@PutMapping("view/{no}")
		public ResponseEntity<String> updateView(@PathVariable("no") int no) throws Exception{
			logger.debug("updateView 호출 " + no);
			if(boardService.updateView(no)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		}
}
