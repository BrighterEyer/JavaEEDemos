drop database if exists test;
CREATE DATABASE `test` DEFAULT CHARACTER SET utf8;
use test;
CREATE TABLE `logs` (
  `class` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `loglevel` varchar(10) NOT NULL,
  `msg` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
