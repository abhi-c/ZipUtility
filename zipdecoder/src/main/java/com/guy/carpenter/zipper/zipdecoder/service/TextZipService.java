package com.guy.carpenter.zipper.zipdecoder.service;

import static com.guy.carpenter.zipper.zipdecoder.utils.ZipUtils.extract;
import static com.guy.carpenter.zipper.zipdecoder.utils.ZipUtils.extractAccracy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.h2.util.StringUtils;
import org.springframework.stereotype.Service;

import com.guy.carpenter.zipper.zipdecoder.entity.ACURACY;
import com.guy.carpenter.zipper.zipdecoder.entity.ZipAbstractVO;
import com.guy.carpenter.zipper.zipdecoder.entity.ZipByStateVO;
import com.guy.carpenter.zipper.zipdecoder.entity.ZipDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TextZipService implements ZipServiceI {

	List<ZipDetail> dataMap = Collections.emptyList();
	Map<String,ZipByStateVO> groupByStateLst = Collections.emptyMap();
	Map<String,ZipAbstractVO> groupByZipLst = Collections.emptyMap();
	
	@Override
	public boolean updateZipDetails() throws IOException {
		Path resourceDirectory = Paths.get("src","main","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		readTestFileAndPopulateDataMap(absolutePath + "/US.txt");
		return true;
	}

	public boolean readTestFileAndPopulateDataMap(String path)
			throws IOException {
		try (BufferedReader buf = new BufferedReader(new FileReader(path))) {
			dataMap = new ArrayList<>();
			groupByStateLst = new HashMap<>();
			groupByZipLst = new HashMap<>();
			
			String line = null;
			int cnt = 1;
			while (true) {
				line = buf.readLine();
				if (line == null) {
					break;
				} else {
					ZipDetail detail = parseLine(line, cnt);
					dataMap.add(detail);
					
					if(groupByStateLst.containsKey(detail.getAdminCode1()))
					{
						ZipByStateVO zipByStateVO = groupByStateLst.get(detail.getAdminCode1());
						zipByStateVO.addCounties(detail.getAdminCode2());
						zipByStateVO.addZipCode(detail.getPostalCode());
					} else {
						ZipByStateVO zipByState = new ZipByStateVO(detail.getPlaceName(), detail.getAdminCode1());
						zipByState.addCounties(detail.getAdminCode2());
						zipByState.addZipCode(detail.getPostalCode());
						groupByStateLst.put(detail.getAdminCode1(), zipByState);
					}
					
					
					ZipAbstractVO zipAbstract = new ZipAbstractVO(detail.getPostalCode(), detail.getPlaceName(), detail.getAdminName2(), detail.getAdminName1(), detail.getAdminCode1());
					groupByZipLst.put(detail.getPostalCode(), zipAbstract);
					
					log.debug("Extracted zip details object" + detail);
				}
				cnt++;
			}
			// buf.close();

		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			throw e;
		}
		return true;
	}

	private ZipDetail parseLine(String line, int cnt) throws ArrayIndexOutOfBoundsException { // this can be extracted to and interface and multiple implementations can be used
		String[] wordsArray;
		wordsArray = line.split("\t");
		if (wordsArray.length > 12) {
			throw new ArrayIndexOutOfBoundsException(
					"Each line of zip details shold have 12 elements and 11 '\\t' for line number: "
							+ cnt + " and line is: " + line);
		}
		ZipDetail detail = new ZipDetail(cnt,
				extract(wordsArray, 0, "unknown Country code"),
				extract(wordsArray, 1, "unknown postalCode"),
				extract(wordsArray, 2, "unknown placeName"),
				extract(wordsArray, 3, "unknown adminName1"),
				extract(wordsArray, 4, "unknown adminCode1"),
				extract(wordsArray, 5, "unknown adminName2"),
				extract(wordsArray, 6, "unknown adminCode2"),
				extract(wordsArray, 7, "unknown adminName3"),
				extract(wordsArray, 8, "unknown adminCode3"),
				extract(wordsArray, 9, "unknown latitude"),
				extract(wordsArray, 10, "unknown longitude"),
				extractAccracy(wordsArray, 11, ACURACY.estimated));
		return detail;
	}

	@Override
	public List<ZipDetail> getData() throws IOException {
		if(dataMap.size() < 1)
		{
			updateZipDetails();
		}
		List<ZipDetail> dataMapTmp = new ArrayList<>();
		for (ZipDetail zipDetail : dataMap) {
			dataMapTmp.add(zipDetail);
		}
		return dataMapTmp;
	}

	@Override
	public ZipByStateVO getByState(String stateCode) throws IllegalArgumentException {
		if(StringUtils.isNullOrEmpty(stateCode)) { throw new IllegalArgumentException("stateCode should not be empty of null"); }
		ZipByStateVO stateVO = groupByStateLst.get(stateCode.toUpperCase());
		
		if(stateVO == null)
			throw new IllegalArgumentException("Data not found for the statecode: " + stateCode);
		
		return stateVO;
	}

	@Override
	public ZipAbstractVO getByPostalCode(String postalCode)
			throws IllegalArgumentException {
		ZipAbstractVO zipAbstractVO = groupByZipLst.get(postalCode);
		
		if(zipAbstractVO == null)
			throw new IllegalArgumentException("Data not found for the postalcode: " + postalCode);
		
		return zipAbstractVO;
	}
}
