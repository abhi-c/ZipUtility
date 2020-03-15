package com.guy.carpenter.zipper.zipdecoder.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.h2.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.guy.carpenter.zipper.zipdecoder.entity.ACURACY;
import com.guy.carpenter.zipper.zipdecoder.entity.ZipDetail;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TextZipService implements ZipServiceI {

	List<ZipDetail> dataMap = Collections.emptyList();

	@Override
	public boolean updateZipDetails() throws Exception {
		Path resourceDirectory = Paths.get("src","main","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		readTestFileAndPopulateDataMap(absolutePath + "/US.txt");
		return true;
	}

	public boolean readTestFileAndPopulateDataMap(String path)
			throws Exception {
		try (BufferedReader buf = new BufferedReader(new FileReader(path))) {
			dataMap = new ArrayList<>();
			/* ArrayList<String> words = new ArrayList<>(); */
			String line = null;
			String[] wordsArray;
			int cnt = 1;
			while (true) {
				line = buf.readLine();
				if (line == null) {
					break;
				} else {
					wordsArray = line.split("\t");
					if (wordsArray.length > 12) {
						throw new ArrayIndexOutOfBoundsException(
								"Each line of zip details shold have 12 elements and 11 '\\t' for line number: "
										+ cnt + " and line is: " + line);
					}
					ZipDetail detail = new ZipDetail(cnt++,
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
					dataMap.add(detail);
					log.debug("Extracted zip details object" + detail);
					/*
					 * for (String each : wordsArray) { if (!"".equals(each)) {
					 * words.add(each); } }
					 */
				}
			}
			// buf.close();

		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	private ACURACY extractAccracy(String[] arr, int index, ACURACY default_) {
		if (arr.length > index && !StringUtils.isNullOrEmpty(arr[index])) {
			try {
				return ACURACY.valueOf(arr[index]);
			} catch (Exception e) {
				return default_;
			}
		}
		return default_;
	}

	private String extract(String[] arr, int index, String default_) {
		if (arr.length > index || StringUtils.isNullOrEmpty(arr[index])) {
			return default_;
		} else {
			return arr[index];
		}
	}

	@Override
	public List<ZipDetail> getData() throws Exception {
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
}
