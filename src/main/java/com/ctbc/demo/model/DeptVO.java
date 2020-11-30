package com.ctbc.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dept_TB")
public class DeptVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "deptno")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deptNo;

	@Column(name = "dname", length = 14)
	private String deptName;

	@Column(name = "loc", length = 13)
	private String deptLoc;

	public DeptVO() {
		super();
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptLoc() {
		return deptLoc;
	}

	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}

	@Override
	public String toString() {
		return "DeptVO [deptNo=" + deptNo + ", deptName=" + deptName + ", deptLoc=" + deptLoc + "]";
	}
	
}
