package com.ctbc.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
