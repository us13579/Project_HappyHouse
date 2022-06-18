package com.ssafy.vue.dto;

/**
 * @author us13579
 *
 */
public class SelectHouseDto {
	private String apartmentName;
	private int buildYear;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String roadName;
	private String roadNameBonbun;
	private String jibun;
	private String lng;
	private String lat;

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}

	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "SelectHouseDto [apartmentName=" + apartmentName + ", buildYear=" + buildYear + ", sidoName=" + sidoName
				+ ", gugunName=" + gugunName + ", dongName=" + dongName + ", roadName=" + roadName + ", roadNameBonbun="
				+ roadNameBonbun + ", jibun=" + jibun + ", lng=" + lng + ", lat=" + lat + "]";
	}

}
