package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.AptLikeDto;
import com.ssafy.vue.dto.AptLikeListDto;
import com.ssafy.vue.dto.BaseaddressDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.LocalLikeDto;
import com.ssafy.vue.dto.UserDto;
import com.ssafy.vue.mapper.UserLikeMapper;
import com.ssafy.vue.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

//	@Autowired
//	private MemberMapper memberMapper;
//	
//	@Override
//	public MemberDto login(MemberDto memberDto) throws Exception {
//		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
//			return null;
//		return memberMapper.login(memberDto);
//	}
//
//	@Override
//	public MemberDto userInfo(String userid) throws Exception {
//		return memberMapper.userInfo(userid);
//	}
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserLikeMapper userLikeMapper;
	
	@Override
	public boolean insertUser(UserDto user) throws Exception{
		if(user.getId() == null || user.getPassword() == null || user.getEmail() == null || user.getName() == null)
			return false;
		return userMapper.insertUser(user) == 1;
	}

	@Override
	public UserDto login(UserDto UserDto) throws Exception {
		if(UserDto.getId() == null || UserDto.getPassword() == null)
			return null;
		return userMapper.login(UserDto);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return userMapper.userInfo(userid);
	}

	@Override
	public List<UserDto> getUserList() throws Exception {
		return userMapper.getUserList();
	}

	@Override
	public boolean isUsedId(String id) throws Exception {
		return userMapper.isUsedId(id) == 0;
	}

	@Override
	public boolean updateUser(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		return  userMapper.updateUser(user) == 1;
	}

	@Override
	public boolean deleteUser(String id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.deleteUser(id) == 1;
	}

// ------------------	user 좋아요 관련 Service ------------------
	
	@Override
	public boolean addLikeArea(LocalLikeDto area) throws Exception {
		return userLikeMapper.addLikeArea(area) == 1;
	}

	@Override
	public boolean addAptArea(AptLikeDto apt) throws Exception {
		return userLikeMapper.addAptArea(apt) == 1;
	}

	@Override
	public boolean deleteLikeArea(int no) throws Exception {	
		return userLikeMapper.deleteLikeArea(no) == 1;
	}

	@Override
	public boolean deleteAptArea(int no) throws Exception {
		return userLikeMapper.deleteAptArea(no) == 1;
	}

	@Override
	public List<BaseaddressDto> getLikeArea(String id) throws Exception {
		return userLikeMapper.getLikeArea(id);
	}

	@Override
	public List<AptLikeListDto> getLikeApt(String id) throws Exception {
		return userLikeMapper.getLikeApt(id);
	}


}
