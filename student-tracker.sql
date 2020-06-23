create database if not exists hb_student_tracker;

use hb_student_tracker;

drop table if exists student;

create table student (
	id integer not null auto_increment,
    first_name varchar(45) default null,
    last_name varchar(45) default null,
    email varchar(45) default null,
    primary key (id)
);


insert into student(first_name,last_name,email)
values('Sam','Jack','samjack');

alter table student
auto_increment=1000;

truncate table student;