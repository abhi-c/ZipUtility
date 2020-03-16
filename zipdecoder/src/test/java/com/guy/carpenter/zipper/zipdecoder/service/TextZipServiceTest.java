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

import com.guy.carpenter.zipper.zipdecoder.entity.ZipAbstractVO;
import com.guy.carpenter.zipper.zipdecoder.entity.ZipByStateVO;

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
	
	@Test
	public final void test_ByStateGood_Objects() throws Exception {
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		assertTrue(service.readTestFileAndPopulateDataMap(absolutePath + "/US.txt"));
		ZipByStateVO byState = service.getByState("NJ");
		assertTrue(" NJ should be present in by State" , (byState != null));
		assertTrue(" By state NJ has 21 counties" , (byState.getNumberOfCounties() == 21));
		assertTrue(" by state NJ has 723 postalCodes" , (byState.getNumberOfZipCodes() == 723));
		
		
		byState = service.getByState("tx");
		assertTrue(" TX should be present in by State" , (byState != null));
		assertTrue(" By state TX has 254 counties" , (byState.getNumberOfCounties() == 254));
		assertTrue(" by state NJ has 2598 postalCodes" , (byState.getNumberOfZipCodes() == 2598));
	}
	
	
	@Test
	public final void test_ByPostalCode_Good_Objects() throws Exception {
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		assertTrue(service.readTestFileAndPopulateDataMap(absolutePath + "/US.txt"));
		
		ZipAbstractVO byZipCode = service.getByPostalCode("99553");
		assertTrue(" NJ should be present in by State" , (byZipCode != null));
		assertTrue(" By state NJ has 21 counties" , (byZipCode.getCountyName().equalsIgnoreCase("Aleutians East")));
		assertTrue(" by state NJ has 723 postalCodes" , (byZipCode.getPlaceName().equalsIgnoreCase("Akutan")));
		
		
		byZipCode = service.getByPostalCode("99505");
		assertTrue(" TX should be present in by State" , (byZipCode != null));
		assertTrue(" By state TX has 254 counties" , (byZipCode.getCountyName().equalsIgnoreCase("Anchorage Municipality")));
		assertTrue(" by state NJ has 2598 postalCodes" , (byZipCode.getPlaceName().equalsIgnoreCase("Jber")));
		assertTrue(" by state NJ has 2598 postalCodes" , (byZipCode.getStateCode().equalsIgnoreCase("AK")));
		assertTrue(" by state NJ has 2598 postalCodes" , (byZipCode.getStateName().equalsIgnoreCase("Alaska")));
	}
	
	@Test(expected= ArrayIndexOutOfBoundsException.class)
	public final void test_readBadFile_content() throws Exception {
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		assertTrue(service.readTestFileAndPopulateDataMap(absolutePath + "/US_ArrayIDOutExc.txt"));
	}

}
