
package com.guy.carpenter.zipper.zipdecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan("com.guy.carpenter.zipper.zipdecoder.entity")
//@EnableAutoConfiguration
public class ZipDecoderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipDecoderApplication.class, args);
	}

}
