package com.ctbc.demo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest // @SpringBootTest的用途為找到@SpringBootApplication主配置類別來啟動Spring Boot應用程式環境 // ref.
				// Could not find an 'annotation declaring class' for annotation type
class StringEncryptorTest {

	@Autowired
	@Qualifier(value = "jasyptStringEncryptor")
	private StringEncryptor stringEncryptor;
	
	@BeforeEach
	public void initEach(TestInfo testInfo) {
		System.out.println(" ======================================================== ");
		System.out.println(" === " + testInfo.getDisplayName() + " === ");
		System.out.println(" ======================================================== ");
	}

	@Test
//	@Disabled
	/**
	 * 將加密完的密碼填入application.properties中
	 * 用 ENC(xxx) 這樣的格式
	 */
	void test000() {
		// https://segmentfault.com/a/1190000007141178
		System.out.println(" === jasypt.encryptor 加密 ===");
		String username = "sa";
		String password = "sa123456";
		System.out.println("encUsername = " + stringEncryptor.encrypt(username));
		System.out.println("encPassword = " + stringEncryptor.encrypt(password));
		
	}

}
