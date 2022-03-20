create sequence jpa_board_seq
    start with 1
    increment by 1
    minvalue 1
    maxvalue 10000;

create table fileload(
                         id number primary key not null,
                         filename varchar2(255) not null,
                         orginfilename varchar2(255) not null,
                         filepath varchar2(255) not null
);

create table jpaboard(
                         num number primary key not null,
                         ref number,
                         restep number,
                         readcount number,
                         regdate date not null,
                         nickname varchar2(30) not null,
                         subject varchar2(50) not null,
                         content varchar2(200) not null,
                         id number,
                         constraint fileid foreign key(id) references fileload(id)
);

insert into jpaboard values (jpa_board_seq.nextval, 0, 0, 0, to_date(sysdate), 'speae', 'speae', 'speae', null);

select * from jpaboard;

drop table jpaboard;
create sequence jpa_board_seq
    start with 1
    increment by 1
    minvalue 1
    maxvalue 10000;

create table fileload(
                         id number primary key not null,
                         filename varchar2(255) not null,
                         orginfilename varchar2(255) not null,
                         filepath varchar2(255) not null
);

create table jpaboard(
                         num number primary key not null,
                         ref number,
                         restep number,
                         readcount number,
                         regdate date not null,
                         nickname varchar2(30) not null,
                         subject varchar2(50) not null,
                         content varchar2(200) not null,
                         id number,
                         constraint fileid foreign key(id) references fileload(id)
);

insert into jpaboard values (jpa_board_seq.nextval, 0, 0, 0, to_date(sysdate), 'speae', 'speae', 'speae', null);

select * from jpaboard;

drop table jpaboard;
