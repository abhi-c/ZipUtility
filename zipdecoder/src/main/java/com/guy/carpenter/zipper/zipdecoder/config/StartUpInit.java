package com.guy.carpenter.zipper.zipdecoder.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guy.carpenter.zipper.zipdecoder.service.TextZipService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StartUpInit {
	@Autowired
	TextZipService service;

	@PostConstruct
	public void init() {
		log.info("initialing TextZip Service");
		try {
			int size = service.getData().size();
			if (size < 1) {
				log.error(
						"TextZip Service has zero data please contact your Administrator");
			} else {
				log.info("TextZip was initialized. Total zips found were: "
						+ size);
			}
		} catch (Exception e) {
			log.error(
					"TextZip Service could not be initialized please contact your Administrator");
		}
	}
}