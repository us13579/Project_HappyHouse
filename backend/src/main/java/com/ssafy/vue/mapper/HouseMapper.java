package com.ssafy.vue.mapper;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.HouseDealDto;
import com.ssafy.vue.dto.SelectHouseDto;
import com.ssafy.vue.dto.YearListHouseDto;

@Mapper
public interface HouseMapper {
	
	List<SelectHouseDto> selectHouse(BigInteger aptCode) throws SQLException;
	List<HouseDealDto> listHouse(BigInteger aptCode) throws Exception;
	List<HouseDealDto> OneYearlistHouse(BigInteger aptCode) throws Exception;
	List<YearListHouseDto> YearlistHouse(BigInteger aptCode) throws Exception;
}
