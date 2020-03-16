package com.guy.carpenter.zipper.zipdecoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guy.carpenter.zipper.zipdecoder.entity.ZipAbstractVO;
import com.guy.carpenter.zipper.zipdecoder.entity.ZipByStateVO;
import com.guy.carpenter.zipper.zipdecoder.service.ZipServiceI;

@RestController
public class ZipController {
	
	@Autowired
	ZipServiceI zipService;
	
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public boolean uploadZipFile() {
		//ToDO
		return false;
	}
	
	@RequestMapping(value = "/zip/{postalCode}", method = RequestMethod.GET)
	public ZipAbstractVO getDetails(@PathVariable("postalCode") String postalCode) throws Exception
	{
		//TODO
		return zipService.getByPostalCode(postalCode.toUpperCase());
	}
	
	@RequestMapping(value = "/zipcountry/count/{stateCode}", method = RequestMethod.GET)
	public ZipByStateVO getCounty(@PathVariable("stateCode") String stateCode) throws Exception
	{
		//TODO
		return zipService.getByState(stateCode.toUpperCase());
	}
}
