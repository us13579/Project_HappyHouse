package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<SidoGugunCodeDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptList(String dong) throws SQLException;
	SidoGugunCodeDto getAreaName(String dong) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<SidoGugunCodeDto> getPopularArea() throws Exception;
}
