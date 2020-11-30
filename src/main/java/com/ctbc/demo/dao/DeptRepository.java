package com.ctbc.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctbc.demo.model.DeptVO;

// ref. https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/13854/
public interface DeptRepository extends JpaRepository<DeptVO, Integer> {
	public List<DeptVO> findAll();

	public DeptVO findByDeptNo(Integer deptNo);

	public Optional<DeptVO> findById(Integer id);
	
	public List<DeptVO> findByDeptNoLessThanEqual(Integer deptNo);
	
	public List<DeptVO> findByDeptNameLike(String deptNameKeyword);
	
	public List<DeptVO> findByDeptNameLikeAndDeptNoGreaterThan(String deptNameKeyword, Integer deptNo);
}
