package com.guy.carpenter.zipper.zipdecoder.service;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TextZipServiceTest {
	
	@Autowired
	private TextZipService service;

	@Test(expected= FileNotFoundException.class)
	public final void test_readInvalidFile() throws Exception {
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		assertTrue(service.readTestFileAndPopulateDataMap(absolutePath + "/US_GhostFile.txt"));
	}
	
	@Test
	public final void test_readGood_Objects() throws Exception {
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		assertTrue(service.readTestFileAndPopulateDataMap(absolutePath + "/US.txt"));
		assertTrue(" Size of extracted zipDetails should be 41469" , (service.getData().size() == 41469));
	}
	
	@Test(expected= ArrayIndexOutOfBoundsException.class)
	public final void test_readBadFile_content() throws Exception {
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		assertTrue(service.readTestFileAndPopulateDataMap(absolutePath + "/US_ArrayIDOutExc.txt"));
	}

}
