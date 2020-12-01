package com.ctbc.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.ctbc.demo.dao.DeptRepository;

//@RunWith(SpringRunner.class)
@SpringBootTest // @SpringBootTest的用途為找到@SpringBootApplication主配置類別來啟動Spring Boot應用程式環境 // ref.
				// Could not find an 'annotation declaring class' for annotation type
class SpringEhcacheTestApplicationTests {

	@Autowired
	private DeptRepository deptRepository;

	@BeforeEach
	public void initEach(TestInfo testInfo) {
		System.out.println(" ======================================================== ");
		System.out.println(" === " + testInfo.getDisplayName() + " === ");
		System.out.println(" ======================================================== ");
	}

	@Test
	@Disabled
	void test001() {
		System.out.println("deptRepository = " + deptRepository);
	}

	@Test
	void test002() {
		System.out.println(" ================= 查第一次 =================");
		deptRepository.findAll().stream().forEach(System.out::println);
		System.out.println(" ================= 查第二次 ================="); // 若有標註 @Cacheable 則不會發送SQL查第二次
		deptRepository.findAll().stream().forEach(System.out::println);
	}
	
	@Test
	void test003() {
		int deptNo = 80;
		System.out.println(" ================= 查第一次 =================");
		System.out.println(deptRepository.findById(deptNo));
		System.out.println(" ================= 查第二次 ================="); // 若有標註 @Cacheable 則不會發送SQL查第二次
		System.out.println(deptRepository.findById(deptNo));
	}
	
	@Test
	void test004() {
		int deptNo = 80;
		System.out.println(deptRepository.findByDeptNo(deptNo));
	}
	
	@Test
	void test005() {
		int deptNo = 30;
		deptRepository.findByDeptNoLessThanEqual(deptNo).stream().forEach(System.out::println);
	}
	
	@Test
	void test006() {
		String deptNameKeyword = "%務%";
		deptRepository.findByDeptNameLike(deptNameKeyword).stream().forEach(System.out::println);
	}
	
	@Test
	void test007() {
		String deptNameKeyword = "%務%";
		int deptNo = 29;
		deptRepository.findByDeptNameLikeAndDeptNoGreaterThan(deptNameKeyword, deptNo).stream().forEach(System.out::println);
	}
	
	@Test
	void test008() {
		System.out.println(" === 使用 @Query Naitve SQL === ");
		String paramStr = "財務部";
		System.out.println(deptRepository.getDeptVONativeQuery(paramStr));
	}
	
	@Test
	void test009() {
		System.out.println(" === 使用 @Query Naitve SQL 搭配 ？ 傳參數 === ");
		Integer paramStr1 = 20;
		String paramStr2 = "臺灣";
		deptRepository.getDeptVONativeQuery2(paramStr1, paramStr2).forEach(System.out::println);
	}
	
	@Test
	// 排序：https://blog.csdn.net/qq_23660243/article/details/43371969
	void test0010() {
		System.out.println(" === 使用 @Query JPQL + SPEL === ");
		String keyword = "部";
		deptRepository.getDeptVOsJpqlWithSpel(keyword).forEach( data -> {
			System.out.println("data = " + data);
		});
	}
	
	@Test
	// 排序：https://blog.csdn.net/qq_23660243/article/details/43371969
	void test0011() {
		System.out.println(" === 使用 @Query JPQL === ");
		String keyword = "務";
		// -------------------------------------------------
		Order order1 = new Order(Direction.ASC, "deptNo"); // entity的屬性變量
		Order order2 = new Order(Direction.ASC, "deptName"); // entity的屬性變量
		Sort sortObj = Sort.by(order1, order2);
		// -------------------------------------------------
		deptRepository.getDeptVOsJpql(keyword, sortObj).forEach( data -> {
			System.out.println("data = " + data);
		});
	}

}
