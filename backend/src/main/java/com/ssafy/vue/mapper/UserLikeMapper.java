package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.AptLikeDto;
import com.ssafy.vue.dto.AptLikeListDto;
import com.ssafy.vue.dto.BaseaddressDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.LocalLikeDto;

public interface UserLikeMapper {
	
//	------------------ 사용자 like 관련 Mapper ------------------	
	public int addLikeArea(LocalLikeDto area) throws SQLException;
	public int addAptArea(AptLikeDto apt) throws SQLException;
	public int deleteLikeArea(int no) throws SQLException;
	public int deleteAptArea(int no) throws SQLException;
	public List<BaseaddressDto> getLikeArea(String id) throws SQLException;
	public List<AptLikeListDto> getLikeApt(String id) throws SQLException;
}
