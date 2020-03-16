package com.guy.carpenter.zipper.zipdecoder.entity;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class ZipAbstractVO {
	@Transient
	private String zipCode;
	private String placeName; // : 1. order subdivision (state) varchar(100)
	private String countyName; // : 1. order subdivision (state) varchar(20)
	private String stateName; // varchar(180)
	private String stateCode; // : 2. order subdivision (county/province) varchar(100)
}
