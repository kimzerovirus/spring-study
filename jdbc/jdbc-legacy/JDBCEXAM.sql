CREATE DATABASE JDBCEXAM;
SHOW DATABASES;

use JDBCEXAM;

CREATE TABLE ROLE (
role_id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
description varchar(100) 
);

insert into role(role_id, description) values(1,'hello');
show tables;
select * from role;
