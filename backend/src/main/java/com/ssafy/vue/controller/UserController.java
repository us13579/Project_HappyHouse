package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.AptLikeDto;
import com.ssafy.vue.dto.AptLikeListDto;
import com.ssafy.vue.dto.BaseaddressDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.LocalLikeDto;
import com.ssafy.vue.dto.UserDto;
import com.ssafy.vue.service.JwtServiceImpl;
import com.ssafy.vue.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MemberController V1")
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	//	성공, 실패로 결과를 전송을 위한 변수 선언 
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
//  Service 주입
	@Autowired
	private UserService userService;

// Jwt 주입
	@Autowired
	private JwtServiceImpl jwtService;
	
	@ApiOperation(value = "회원 가입")
	@PostMapping
	public ResponseEntity<Boolean> insertUser(@RequestBody UserDto user) throws Exception{
		return new ResponseEntity<Boolean>(userService.insertUser(user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 정보 수정")
	@PutMapping
	public ResponseEntity<Boolean> updateUser(@RequestBody UserDto user) throws Exception{
		return new ResponseEntity<Boolean>(userService.updateUser(user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 삭제")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") String id)throws Exception{
		System.out.println("삭제 in");
		return new ResponseEntity<Boolean>(userService.deleteUser(id), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "아이디 중복 체크")
	@GetMapping("/{id}")
	public ResponseEntity<Boolean> idUsedId(@PathVariable("id") String id) throws Exception{
		return new ResponseEntity<Boolean>(userService.isUsedId(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login( HttpServletRequest request, 
			@RequestBody UserDto user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			UserDto loginUser = userService.login(user);
			status = HttpStatus.ACCEPTED;
			if (loginUser != null) {
				String token = jwtService.create("id", loginUser.getId(), "access-token");// key, data, subject
//				logger.debug("로그인 토큰정보 : {}", token);
				System.out.println("로그인 토큰정보 : " + token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
//			logger.error("로그인 실패 : {}", e);
			System.out.println("로그인 실패 : " +  e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String id,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
//			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(id);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
//				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
//			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "사용자 전체 목록 불러오기")
	@GetMapping("/list")
	public ResponseEntity<List<UserDto>> getInfo() throws Exception{
		List<UserDto> userList = userService.getUserList();
		return new ResponseEntity<List<UserDto>>(userList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자가 관심있는 지역 ")
	@PostMapping("/like/area")
	public ResponseEntity<Boolean> addLikeArea(@RequestBody LocalLikeDto area) throws Exception{
		System.out.println(area);
		return new ResponseEntity<Boolean>(userService.addLikeArea(area), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자가 관심있는 아파트")
	@PostMapping("/like/apt")
	public ResponseEntity<Boolean> addLikeApt(@RequestBody AptLikeDto apt) throws Exception{
		System.out.println(apt);
		return new ResponseEntity<Boolean>(userService.addAptArea(apt), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자가 관심있는 지역 리스트 가져오기")
	@GetMapping("/like/area/{id}")
	public ResponseEntity<List<BaseaddressDto>> getLikeArea(@PathVariable("id") String id) throws Exception{
		return new ResponseEntity<List<BaseaddressDto>>(userService.getLikeArea(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자가 관심있는 아파트 리스트 가져오기")
	@GetMapping("/like/apt/{id}")
	public ResponseEntity<List<AptLikeListDto>> getLikeApt(@PathVariable("id") String id) throws Exception{
		return new ResponseEntity<List<AptLikeListDto>>(userService.getLikeApt(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자가 관심있는 지역 ")
	@DeleteMapping("/like/area/{no}")
	public ResponseEntity<Boolean> deleteLikeArea(@PathVariable("no") int no) throws Exception{
		return new ResponseEntity<Boolean>(userService.deleteLikeArea(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자가 관심있는 아파 ")
	@DeleteMapping("/like/apt/{no}")
	public ResponseEntity<Boolean> deleteLikeApt(@PathVariable("no") int no) throws Exception{
		return new ResponseEntity<Boolean>(userService.deleteAptArea(no), HttpStatus.OK);
	}

}
//	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
//	private static final String SUCCESS = "success";
//	private static final String FAIL = "fail";
//
//	@Autowired
//	private JwtServiceImpl jwtService;
//
//	@Autowired
//	private MemberService memberService;
//
//	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
//	@PostMapping("/login")
//	public ResponseEntity<Map<String, Object>> login(
//			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		try {
//			MemberDto loginUser = memberService.login(memberDto);
//			if (loginUser != null) {
//				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
//				logger.debug("로그인 토큰정보 : {}", token);
//				resultMap.put("access-token", token);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} else {
//				resultMap.put("message", FAIL);
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (Exception e) {
//			logger.error("로그인 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//
//	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
//	@GetMapping("/info/{userid}")
//	public ResponseEntity<Map<String, Object>> getInfo(
//			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
//			HttpServletRequest request) {
////		logger.debug("userid : {} ", userid);
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		if (jwtService.isUsable(request.getHeader("access-token"))) {
//			logger.info("사용 가능한 토큰!!!");
//			try {
////				로그인 사용자 정보.
//				MemberDto memberDto = memberService.userInfo(userid);
//				resultMap.put("userInfo", memberDto);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} catch (Exception e) {
//				logger.error("정보조회 실패 : {}", e);
//				resultMap.put("message", e.getMessage());
//				status = HttpStatus.INTERNAL_SERVER_ERROR;
//			}
//		} else {
//			logger.error("사용 불가능 토큰!!!");
//			resultMap.put("message", FAIL);
//			status = HttpStatus.ACCEPTED;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
