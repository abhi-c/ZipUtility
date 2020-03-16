package com.guy.carpenter.zipper.zipdecoder.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Transient;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
//@Data
@ToString
public class ZipByStateVO {

	public ZipByStateVO(String stateName, String stateCode) {
		this.stateCode = stateCode;
		this.stateName = stateName;
	}

	@Transient
	private Set<String> counties = new HashSet<>();
	@Transient
	private Set<String> zipCodes = new HashSet<>();
	private String stateName; // : 1. order subdivision (state) varchar(100)
	private String stateCode; // : 1. order subdivision (state) varchar(20)
/*	private int numberOfCounties = 0; // varchar(180)
	private int numberOfZipCodes = 0; // : 2. order subdivision
*/										// (county/province) varchar(100)
	public int getNumberOfCounties() {
		return counties.size();
	}
	
	public int getNumberOfZipCodes() {
		return zipCodes.size();
	}
	
	public void addCounties(String county) {
		this.counties.add(county);
	}
	
	public void addZipCode(String zip) {
		this.zipCodes.add(zip);
	}
}
