show database;

create database baord;

create user 'test'@'localhost' identified by 'password';
select `user` from `mysql`.`user`;
show grants for 'test'@'localhost';
grant all on `board`.* to 'test'@'localhost' with grant option;

flush privileges; -- 권한을 주고서 안되면 이걸로 실행해주면 됨