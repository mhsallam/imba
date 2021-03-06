package com.mhsallam.imba;

import com.mhsallam.imba.util.EnumPropUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties(value = EnumPropUtil.class)
@SpringBootApplication
@ComponentScan
public class ImswapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImswapApplication.class, args);
	}

}
