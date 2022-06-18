package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface HouseMapMapper {
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<SidoGugunCodeDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptList(String dong) throws SQLException;
	SidoGugunCodeDto getAreaName(String dong) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<SidoGugunCodeDto> getPopularArea() throws SQLException;
}
