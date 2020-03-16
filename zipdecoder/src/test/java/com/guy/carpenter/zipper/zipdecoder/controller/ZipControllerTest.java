package com.guy.carpenter.zipper.zipdecoder.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(ZipController.class)
public class ZipControllerTest {

	@Autowired
    private MockMvc mvc;
 
    /*@Autowired
    private TextZipService service;*/
    
	@Test
	public final void testByStateCA() throws Exception {
		mvc.perform(get("/zipcountry/count/CA")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.length()", is(2)))
			      .andExpect(jsonPath("$.numberOfZipCodes", is(2591)))
			      .andExpect(jsonPath("$.numberOfCounties", is(58)));
	}

	@Test
	public final void testByPostalCode43035() throws Exception {
		mvc.perform(get("/zip/43035")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.length()", is(5)))
			      .andExpect(jsonPath("$.placeName", is("Lewis Center")))
			      .andExpect(jsonPath("$.countyName", is("Delaware")));
	}
	
}
