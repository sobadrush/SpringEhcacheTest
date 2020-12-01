USE DB_Emp_Dept;
--DROP TABLE emp_TB;
--DROP TABLE dept_TB;

--SELECT * FROM dept_TB;
--SELECT * FROM emp_TB ORDER by deptno;

CREATE TABLE dept_TB
(
    deptno	int IDENTITY(10,10) PRIMARY KEY  not null,
	dname	varchar(14),
	loc		varchar(13)
);
CREATE TABLE emp_TB
(
    empno	 int IDENTITY(7001,1) PRIMARY KEY not null,
	ename	 varchar(14),
	job		 varchar(13),
	hiredate  date,
	--deptno int not NULL
	deptno int not NULL FOREIGN KEY REFERENCES dept_TB(deptno)
	--Hibernate 做關聯查詢其實可以不用在實體表格設定foreign-key
);

insert into dept_TB(dname, loc) values ('財務部','臺灣台北');
insert into dept_TB(dname, loc) values ('研發部','臺灣新竹');
insert into dept_TB(dname, loc) values ('業務部','美國紐約');
insert into dept_TB(dname, loc) values ('生管部','中國上海');
insert into dept_TB (dname, loc) values('法務部', '南投埔里');
insert into dept_TB (dname, loc) values('總務處', '台北松山');
insert into dept_TB (dname, loc) values('應用系統部', '臺灣南港');


insert into emp_TB(ename, job, hiredate, deptno) values ('king','president','1981-11-17',10);
insert into emp_TB(ename, job, hiredate, deptno) values ('blake','manager','1981-05-01',30);
insert into emp_TB(ename, job, hiredate, deptno) values ('clark','manager','1981-01-09',10);
insert into emp_TB(ename, job, hiredate, deptno) values ('jones','manager','1981-04-02',20);
insert into emp_TB(ename, job, hiredate, deptno) values ('martin','salesman','1981-09-28',30);
insert into emp_TB(ename, job, hiredate, deptno) values ('allen','salesman','1981-02-2',30);
insert into emp_TB(ename, job, hiredate, deptno) values ('turner','salesman','1981-09-28',30);
insert into emp_TB(ename, job, hiredate, deptno) values ('james','clerk','1981-12-03',30);
insert into emp_TB(ename, job, hiredate, deptno) values ('ward','salesman','1981-02-22',30);
insert into emp_TB(ename, job, hiredate, deptno) values ('ford','analyst','1981-12-03',20);
insert into emp_TB(ename, job, hiredate, deptno) values ('smith','clerk','1980-12-17',20);
insert into emp_TB(ename, job, hiredate, deptno) values ('scott','analyst','1981-12-09',20);
insert into emp_TB(ename, job, hiredate, deptno) values ('adams','clerk','1983-01-12',20);
insert into emp_TB(ename, job, hiredate, deptno) values ('miller','clerk','1982-01-23',10);


