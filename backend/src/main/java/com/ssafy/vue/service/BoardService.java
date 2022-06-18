package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> listBoard() throws Exception;
	public BoardDto selectBoard(int articleno) throws Exception;
	public boolean insertBoard(BoardDto boardDto) throws Exception;
	public boolean updateBoard(BoardDto boardDto) throws Exception;
	public boolean deleteBoard(int articleno) throws Exception;
	public String selectTag(int no) throws Exception;
	public boolean updateView(int no) throws Exception;
}
