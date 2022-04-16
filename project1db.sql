create database project1db;

create table employees(
eid int generated always as identity,
fname varchar(50),
lname varchar(50),
ph_num varchar(20),
dpt varchar(30),
title varchar(30),
salary int,
primary key(eid)
);

create table expense_records(
recordNo int generated always as identity,
eid int, 
expense_type varchar(20), -- enum education, food/travel, supplies,
item varchar(30),
amount numeric(10,2),
status varchar(20), -- enum pending, approved, denied
primary key (recordNo),
constraint fk_employees
foreign key (eid)
	references employees(eid)
	on delete cascade
);

drop table expense_records;
drop table employees;
