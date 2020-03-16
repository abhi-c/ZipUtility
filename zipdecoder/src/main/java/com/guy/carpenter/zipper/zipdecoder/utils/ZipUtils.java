package com.guy.carpenter.zipper.zipdecoder.utils;

import org.h2.util.StringUtils;

import com.guy.carpenter.zipper.zipdecoder.entity.ACURACY;

public class ZipUtils {

	public static ACURACY extractAccracy(String[] arr, int index, ACURACY default_) {
		if (arr.length > index && !StringUtils.isNullOrEmpty(arr[index])) {
			try {
				return ACURACY.valueOf(arr[index]);
			} catch (Exception e) {
				return default_;
			}
		}
		return default_;
	}

	public static String extract(String[] arr, int index, String default_) {
		if (arr.length < index || StringUtils.isNullOrEmpty(arr[index])) {
			return default_;
		} else {
			return arr[index];
		}
	}
}
