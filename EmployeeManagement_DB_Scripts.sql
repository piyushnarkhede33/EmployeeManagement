create database employee_management;

CREATE TABLE `employee` (
  `emp_id` bigint NOT NULL AUTO_INCREMENT,
  `department` varchar(255) NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) NOT NULL,
  PRIMARY KEY (`emp_id`)
);

INSERT INTO employee (department, email_id, first_name, last_name, phone_no) VALUES (
'HR', 'akshay.kadam@gmail.com', 'Akshay', 'Kadam', '9489784511');

INSERT INTO employee (department, email_id, first_name, last_name, phone_no) VALUES (
'IT', 'mohit.patil', 'Mohit', 'Narkhede', '7898986666');


CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email_id` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_no` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_role` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_user_name` (`user_name`)
);

INSERT INTO user (email_id, first_name, last_name, password, phone_no, user_name, user_role) VALUES (
'piyush.narkhede@gmail.com', 'Piyush', 'Narkhede', '$2a$10$UdD1Nt7HufjNlpAABHx2auLbOIaIfLytbulgEnop4ZS6kMV7UoOFW', '7420982309', 'piyush.narkhede', 'ADMIN');
INSERT INTO user (email_id, first_name, last_name, password, phone_no, user_name, user_role) VALUES (
'hitesh.narkhede@gmail.com', 'Hitesh', 'Narkhede', '$2a$10$UdD1Nt7HufjNlpAABHx2auLbOIaIfLytbulgEnop4ZS6kMV7UoOFW', '9767259946', 'hitesh.narkhede', 'USER');
INSERT INTO user (email_id, first_name, last_name, password, phone_no, user_name, user_role) VALUES (
'Admin123@gmail.com', 'Admin', NULL, '$2a$10$UdD1Nt7HufjNlpAABHx2auLbOIaIfLytbulgEnop4ZS6kMV7UoOFW', '9767259946', 'ADMIN', 'ADMIN');
INSERT INTO user (email_id, first_name, last_name, password, phone_no, user_name, user_role) VALUES (
'test.user@dev.com', 'test', 'user', '$2a$10$UdD1Nt7HufjNlpAABHx2auLbOIaIfLytbulgEnop4ZS6kMV7UoOFW', '3456876547', 'test.user', 'USER');
INSERT INTO user (email_id, first_name, last_name, password, phone_no, user_name, user_role) VALUES (
'demo.user@dev.com', 'Demo', 'User', '$2a$10$7bCSZSQgY2lsEkEuTRZjre3FUsPtRGe1.N.hjRG83MYY7BXDbAS/G', '987654321', 'Demo.User', 'USER');

