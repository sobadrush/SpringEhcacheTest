package com.ctbc.demo;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableEncryptableProperties
public class SpringEhcacheTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEhcacheTestApplication.class, args);
	}

	@Bean(name = "jasyptStringEncryptor")
	public PooledPBEStringEncryptor stringEncryptor() {
		// Spring Boot Datasource加密
		// ref. https://github.com/ulisesbocchio/jasypt-spring-boot/issues/51
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("secretKey"); // #jasypt 加密用的密鑰
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}

}
