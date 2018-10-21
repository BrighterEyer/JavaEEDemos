drop database if exists smbms;
create database smbms;
use smbms;
create table t_user(
  id int(11) primary key auto_increment,
  userCode varchar(255),
  userName varchar(255),
  userPassword varchar(255),
  birthday date,
  gender int(1),
  phone varchar(255),
  address varchar(255),
  userRole int(11),
  createdBy int(11),
  creationDate date,
  modifyBy int(11),
  modifyDate date,
  age int(3),
  userRoleName varchar(255),
  idPicPath varchar(255),
  workPicPath varchar(255)
);