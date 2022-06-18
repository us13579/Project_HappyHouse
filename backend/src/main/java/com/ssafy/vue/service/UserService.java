package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.AptLikeDto;
import com.ssafy.vue.dto.AptLikeListDto;
import com.ssafy.vue.dto.BaseaddressDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.LocalLikeDto;
import com.ssafy.vue.dto.UserDto;

public interface UserService {
	public boolean insertUser(UserDto user) throws Exception;
	public boolean updateUser(UserDto user) throws Exception;
	public boolean deleteUser(String id) throws Exception;
	public boolean isUsedId(String id) throws Exception;
	public UserDto login(UserDto memberDto) throws Exception;
	public UserDto userInfo(String userid) throws Exception;
	public List<UserDto> getUserList() throws Exception;
//	좋아요 관련 service
	public boolean addLikeArea(LocalLikeDto area) throws Exception;
	public boolean addAptArea(AptLikeDto apt) throws Exception;
	public boolean deleteLikeArea(int no) throws Exception;
	public boolean deleteAptArea(int no) throws Exception;
	public List<BaseaddressDto> getLikeArea(String id) throws Exception;
	public List<AptLikeListDto> getLikeApt(String id) throws Exception;
	}
