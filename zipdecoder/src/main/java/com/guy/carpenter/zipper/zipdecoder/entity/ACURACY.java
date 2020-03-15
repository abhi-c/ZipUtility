package com.guy.carpenter.zipper.zipdecoder.entity;

public enum ACURACY {
	
	estimated(1), geonameid(4), centroid(6);
	
	ACURACY(int val) {
		valueId = val;
	}
	
	private int valueId;
	
	public int getVal() { return valueId ; }
	
}
