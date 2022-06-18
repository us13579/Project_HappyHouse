package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.service.HouseMapService;

@RestController
@RequestMapping("/map")
//@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
//		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
//		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunCodeDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<HouseInfoDto>> getAptList(@RequestParam("dong") String dong) throws Exception {
		System.out.println(dong);
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptList(dong), HttpStatus.OK);
	}
	
	@GetMapping("/area")
	public ResponseEntity<SidoGugunCodeDto> getAreaName(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<SidoGugunCodeDto>(haHouseMapService.getAreaName(dong), HttpStatus.OK);
	}
	
	@GetMapping("/area/popular")
	public ResponseEntity<List<SidoGugunCodeDto>> getPopularArea() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getPopularArea(),HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
}
