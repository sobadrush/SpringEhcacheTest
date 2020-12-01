package com.ctbc.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "dept_TB")
@Data // lombok
@Builder // lombok
@AllArgsConstructor
@NoArgsConstructor // lombok （像是 Spring Data JPA），會需要每個類都一定要有一個無參數的 constructor，所以你在加上 @AllArgsConstructor 時，拜託，一定要補上 @NoArgsConstrcutor，不然會有各種坑等著你
@ToString // lombok
public class DeptVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "deptno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deptNo;

	@Column(name = "dname", length = 14)
	private String deptName;

	@Column(name = "loc", length = 13)
	private String deptLoc;

}
