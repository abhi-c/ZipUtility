package com.guy.carpenter.zipper.zipdecoder.service;

import java.io.IOException;
import java.util.List;

import com.guy.carpenter.zipper.zipdecoder.entity.ZipAbstractVO;
import com.guy.carpenter.zipper.zipdecoder.entity.ZipByStateVO;
import com.guy.carpenter.zipper.zipdecoder.entity.ZipDetail;

public interface ZipServiceI {
	
	public boolean updateZipDetails() throws IOException;	
	
	public List<ZipDetail> getData() throws IOException;	
	
	public ZipByStateVO getByState(String stateCode) throws IllegalArgumentException;	
	
	public ZipAbstractVO getByPostalCode(String postalCode) throws IllegalArgumentException;	

}
