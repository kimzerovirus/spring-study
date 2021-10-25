show databases;
use board_sample;
show tables;

drop table tbl_board;

create table tbl_board(
                          bno bigint(10) not null auto_increment ,
                          title varchar(200) not null,
                          content varchar(2000) not null,
                          writer varchar(50) not null,
                          regdate TIMESTAMP DEFAULT NOW(),
                          updatedate TIMESTAMP DEFAULT NOW(),
                          primary key(bno)
);

select * from tbl_board;

insert into tbl_board (title, content, writer)
values ('테스트 제목','테스트 내용','user00');