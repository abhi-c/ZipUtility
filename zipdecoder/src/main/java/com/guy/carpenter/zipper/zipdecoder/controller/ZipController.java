package com.guy.carpenter.zipper.zipdecoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guy.carpenter.zipper.zipdecoder.entity.ZipDetail;
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
	
	@RequestMapping(value = "/zip/{zipCode}", method = RequestMethod.GET)
	public List<ZipDetail> getDetails(@PathVariable("zipCode") String zipCode) throws Exception
	{
		//TODO
		return zipService.getData();
	}
	
	@RequestMapping(value = "/zipcountry/count/{state}", method = RequestMethod.GET)
	public List<ZipDetail> getCounty(@PathVariable("state") String state) throws Exception
	{
		//TODO
		return zipService.getData();
	}
}
