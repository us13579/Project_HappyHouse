package com.ssafy.vue.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.HouseDealDto;
import com.ssafy.vue.dto.SelectHouseDto;
import com.ssafy.vue.dto.YearListHouseDto;
import com.ssafy.vue.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseMapper houseMapper;
	
	@Override
	public List<SelectHouseDto> selectHouse(BigInteger aptCode) throws Exception {
		return houseMapper.selectHouse(aptCode);
	}

	@Override
	public List<HouseDealDto> listHouse(BigInteger aptCode) throws Exception {
		return houseMapper.listHouse(aptCode);
	}

	@Override
	public List<HouseDealDto> OneYearlistHouse(BigInteger aptCode) throws Exception {
		return houseMapper.OneYearlistHouse(aptCode);
	}

	@Override
	public List<YearListHouseDto> YearlistHouse(BigInteger aptCode) throws Exception {
		return houseMapper.YearlistHouse(aptCode);
	}

}
