package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.AptLikeDto;
import com.ssafy.vue.dto.LocalLikeDto;
import com.ssafy.vue.dto.UserDto;


public interface UserMapper  {

//	public MemberDto login(MemberDto memberDto) throws SQLException;
//	public MemberDto userInfo(String userid) throws SQLException;
	
	public int insertUser(UserDto user) throws SQLException;
	public int updateUser(UserDto user) throws SQLException;
	public int deleteUser(String id) throws SQLException;
	public UserDto login(UserDto user) throws SQLException;
	public UserDto userInfo(String id) throws SQLException;
	public int isUsedId(String id) throws SQLException;	
	public List<UserDto> getUserList() throws SQLException;
}