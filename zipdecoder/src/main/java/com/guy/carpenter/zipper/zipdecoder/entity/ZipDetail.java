package com.guy.carpenter.zipper.zipdecoder.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ZipDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String countryCode; //iso country code, 2 characters
	private String postalCode; //varchar(20)
	private String placeName; // varchar(180)
	private String adminName1; // : 1. order subdivision (state) varchar(100)
	private String adminCode1; // : 1. order subdivision (state) varchar(20)
	private String adminName2; // : 2. order subdivision (county/province) varchar(100)
	private String adminCode2; // : 2. order subdivision (county/province) varchar(20)
	private String adminName3; // : 3. order subdivision (community) varchar(100)
	private String adminCode3; // : 3. order subdivision (community) varchar(20)
	private String latitude; // : estimated latitude (wgs84)
	private String longitude; // : estimated longitude (wgs84)
	private ACURACY accuracy; // : accuracy of lat/lng from 1=estimated, 4=geonameid, 6=centroid of addresses or shape
}
