create database ssh;
use ssh;

create table product(
  id int(11) primary key auto_increment,
  name varchar(255),
  price decimal
);