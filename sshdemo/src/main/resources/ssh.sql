drop database ssh if exists ssh;
create database ssh;
use ssh;

create table product(
  id int(11) primary key auto_increment,
  name varchar(255),
  price decimal
);

create table logins(
  id int(11) primary key auto_increment,
  name varchar(255),
  pwd  varchar(255)
);