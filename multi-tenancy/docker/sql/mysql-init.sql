DROP DATABASE IF EXISTS `tenant_manager`;
CREATE DATABASE `tenant_manager`;

DROP USER IF EXISTS 'tenant_manager'@'localhost';
CREATE USER 'tenant_manager'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON `tenant_manager`.* TO 'tenant_manager'@'%';

DROP DATABASE IF EXISTS `tenant_mysql`;
CREATE DATABASE `tenant_mysql`;

DROP USER IF EXISTS 'tenant_user'@'localhost';
CREATE USER 'tenant_user'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON `tenant_mysql`.* TO 'tenant_user'@'%';

-- create user '사용자'@'host' identified by '비밀번호';
--
-- //ex1) 내부 접근을 허용하는 사용자 추가
-- create user 'test'@'localhost' identified by '0000';
--
-- //ex2) 외부 접근을 허용하는 사용자 추가
-- create user 'test'@'%' identified by '0000';
--
-- //ex3) 특정 ip만 접근을 허용하는 사용자 추가
-- create user 'test'@'123.456.789.100' identified by '0000';
--
-- //ex4) 특정 ip 대역을 허용하는 사용자 추가
-- create user 'test'@'192.168.%' identified by '0000';
