package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> listBoard() throws SQLException;
	public BoardDto selectBoard(int articleno) throws SQLException;
	public int insertBoard(BoardDto boardDto) throws SQLException;
	public int updateBoard(BoardDto boardDto) throws SQLException;
	public int deleteBoard(int articleno) throws SQLException;
	public String selectTag(int no) throws SQLException;
	public int updateView(int no) throws SQLException;
}