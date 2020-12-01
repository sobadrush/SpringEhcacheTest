package com.ctbc.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ctbc.demo.model.DeptVO;

// ref. https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/13854/
@Repository
@CacheConfig(cacheNames = "DeptCache")
public interface DeptRepository extends JpaRepository<DeptVO, Integer> {
	
	@org.springframework.cache.annotation.Cacheable // 純粹 select 資料的方法不需要每次都執行，就可以使用 @Cacheable，直接從 Cache 區取得資料而不執行該方法
	public List<DeptVO> findAll();

	public DeptVO findByDeptNo(Integer deptNo);
	
	@Cacheable(key = "#id") // 表示以輸入參數 id 做為緩存資料的 key 值將 User 物件放入 Cache 區
	public Optional<DeptVO> findById(Integer id);
	
	public List<DeptVO> findByDeptNoLessThanEqual(Integer deptNo);
	
	public List<DeptVO> findByDeptNameLike(String deptNameKeyword);
	
	public List<DeptVO> findByDeptNameLikeAndDeptNoGreaterThan(String deptNameKeyword, Integer deptNo);
	
	// 使用 nativeQuery 查詢
	@Query(nativeQuery = true, value = "SELECT deptno, dname, loc FROM dept_TB WHERE dname = :pDeptName") 
	public DeptVO getDeptVONativeQuery(@Param("pDeptName") String pDeptName);
	
	// 使用 nativeQuery 查詢 + ？ 傳參數
	@Query(nativeQuery = true, value = "SELECT deptno, dname, loc FROM dept_TB WHERE deptno = ?1 AND loc LIKE %?2%") 
	public List<DeptVO> getDeptVONativeQuery2(Integer deptNo, String loc);
	
	// 使用JPQL查詢
	@Query(value = "FROM DeptVO AS dd WHERE dd.deptName LIKE %:keyword%") // https://thorben-janssen.com/spring-data-jpa-query-annotation/
	public List<DeptVO> getDeptVOsJpql(@Param("keyword") String keyword, org.springframework.data.domain.Sort sort);
	
	// 使用JPQL查詢 + SPEL操作
	@Query(value = "FROM DeptVO AS dd WHERE dd.deptName LIKE %?#{[0].toUpperCase()}%") // https://thorben-janssen.com/spring-data-jpa-query-annotation/
	public List<DeptVO> getDeptVOsJpqlWithSpel(String keyword);
	
	@Transactional // 不加會報 Executing an update/delete query， ref. https://blog.csdn.net/jiangyu1013/article/details/80760211
	@Modifying // 除了 SELECT 外都要加 
	@Query(nativeQuery = true, value = "INSERT INTO dept_TB (dname, loc) VALUES( :pDeptName, :pDeptLoc )")
	public int saveDeptVONativeQuery(@Param("pDeptName") String pDeptName, @Param("pDeptLoc") String pDeptLoc);
	
	@Transactional
	@Modifying // 除了 SELECT 外都要加 
	@Query("DELETE FROM DeptVO AS dd WHERE dd.deptNo = :pDeptNo")
	public int deleteByDeptNo(@Param("pDeptNo") Integer deptNo);
	
}

