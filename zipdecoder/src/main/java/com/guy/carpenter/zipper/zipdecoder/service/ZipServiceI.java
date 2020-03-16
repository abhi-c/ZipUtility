package com.guy.carpenter.zipper.zipdecoder.service;

import java.util.List;

import com.guy.carpenter.zipper.zipdecoder.entity.ZipAbstractVO;
import com.guy.carpenter.zipper.zipdecoder.entity.ZipByStateVO;
import com.guy.carpenter.zipper.zipdecoder.entity.ZipDetail;

public interface ZipServiceI {
	
	public boolean updateZipDetails() throws Exception;	
	
	public List<ZipDetail> getData() throws Exception;	
	
	public ZipByStateVO getByState(String stateCode) throws Exception;	
	
	public ZipAbstractVO getByPostalCode(String postalCode) throws Exception;	

}
