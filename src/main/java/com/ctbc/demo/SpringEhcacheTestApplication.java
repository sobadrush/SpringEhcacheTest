package com.ctbc.demo;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // spring boot 會自動配置一個 DataSourceTransactionManager，我們只需在方法（或者類）加上 @Transactional 註解，就自動納入 Spring 的事務管理了 , (ref. https://www.itread01.com/content/1550054167.html)
//@EnableEncryptableProperties
@EnableCaching // For Ehcache
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
