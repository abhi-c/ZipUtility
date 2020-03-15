package com.guy.carpenter.zipper.zipdecoder.service;

import java.util.List;

import com.guy.carpenter.zipper.zipdecoder.entity.ZipDetail;

public interface ZipServiceI {
	
	public boolean updateZipDetails() throws Exception;	
	
	public List<ZipDetail> getData() throws Exception;	

}
