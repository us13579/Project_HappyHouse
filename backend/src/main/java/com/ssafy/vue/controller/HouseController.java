package com.ssafy.vue.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.vue.dto.HouseDealDto;
import com.ssafy.vue.dto.SelectHouseDto;
import com.ssafy.vue.dto.YearListHouseDto;
import com.ssafy.vue.service.HouseService;

@Controller
@RequestMapping("/house")
public class HouseController {

	@Autowired
	private HouseService houseService;

	// 아파트 상세 정보 ( 한 개 가져오기 )
	@GetMapping("{aptCode}")
	public ResponseEntity<List<SelectHouseDto>> selectHouse(@PathVariable("aptCode") String aptCode) throws Exception {
		BigInteger aptCode2 = new BigInteger(aptCode);
		return new ResponseEntity<List<SelectHouseDto>>(houseService.selectHouse(aptCode2), HttpStatus.OK);
	}

	// 아파트 거래 정보 ( 리스트 )
	@GetMapping("/list/{aptCode}")
	public ResponseEntity<List<HouseDealDto>> listHouse(@PathVariable("aptCode") String aptCode) throws Exception {
		BigInteger aptCode2 = new BigInteger(aptCode);
		return new ResponseEntity<List<HouseDealDto>>(houseService.listHouse(aptCode2), HttpStatus.OK);
	}

	// 아파트 거래 정보 ( 리스트 일년 것만 )
	@GetMapping("/list/year/{aptCode}")
	public ResponseEntity<List<HouseDealDto>> OneYearlistHouse(@PathVariable("aptCode") String aptCode) throws Exception {
		BigInteger aptCode2 = new BigInteger(aptCode);
		return new ResponseEntity<List<HouseDealDto>>(houseService.OneYearlistHouse(aptCode2), HttpStatus.OK);
	}

	// 아파트 거래 정보 ( 리스트 년단위 그룹 )
	@GetMapping("/list/group/{aptCode}")
	public ResponseEntity<List<YearListHouseDto>> YearlistHouse(@PathVariable("aptCode") String aptCode) throws Exception {
		BigInteger aptCode2 = new BigInteger(aptCode);
		return new ResponseEntity<List<YearListHouseDto>>(houseService.YearlistHouse(aptCode2), HttpStatus.OK);
	}

}
