package com.mhsallam.imba;

import com.mhsallam.imba.common.AppEnumProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties(value = AppEnumProperties.class)
@SpringBootApplication
@ComponentScan
public class ImswapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImswapApplication.class, args);
	}

}
