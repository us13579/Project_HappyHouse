package com.ssafy.vue.dto;

public class YearListHouseDto {
	private int dealAmount;
	private int count;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;

	public int getdealAmount() {
		return dealAmount;
	}

	public void setdealAmount(int dealAmount) {
		this.dealAmount = dealAmount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "YearListHouseDto [dealAmount=" + dealAmount + ", count=" + count + ", dealYear=" + dealYear
				+ ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + "]";
	}

}
