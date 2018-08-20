create database aiyuncms charset utf8;

use aiyuncms;

create table sys_user(
	id int not null primary key auto_increment,
	account varchar(50) not null,
	password char(32) not null,
	email varchar(100) not null,
	phone varchar(20) not null,
	salt varchar(10),
	addon datetime,
	editon datetime,
	deleteon datetime,
	flag_delete tinyint default 0
)engine=innodb charset=utf8 ;

create table sys_role(
	id int not null primary key auto_increment,
	name varchar(50) not null,
	parent_id int not null,
	addon datetime,
	editon datetime,
	deleteon datetime,
	flag_delete tinyint default 0
)engine=innodb charset=utf8;

create table sys_node(
	id int not null primary key ,
	name varchar(50) not null,
	note varchar(100),
	flag_delete tinyint default 0
)engine=innodb charset=utf8;

create table sys_role_node(
	role_id int not null,
	node_id int not null
)engine=innodb charset=utf8;

create table sys_role_user(
	role_id int not null,
	user_id int not null
)engine=innodb charset=utf8;


create table web_menus(
	id int not null primary key auto_increment,
	title varchar(50) not null,
	parent_id int,
	status tinyint default 0,
	addon datetime,
	editon datetime,
	deleteon datetime,
	flag_delete tinyint default 0
)engine=innodb charset=utf8;

create table web_news(
	id int not null primary key auto_increment,
	menu_id int,
	title varchar(200) not null,
	sub_title varchar(200),
	brief varchar(200),
	content text,
	link_url varchar(200),
	author int,
	read_count int,
	sort_order int default 0,
	keywords varchar(200),
	status tinyint default 0,
	addon datetime,
	editon datetime,
	deleteon datetime,
	flag_delete tinyint default 0
)engine=innodb charset=utf8;

create table web_user(
	id int not null primary key auto_increment,
	name varchar(20),
	alias varchar(50),
	gender varchar(4),
	phone varchar(20),
	email varchar(50),
	wxid varchar(50),
	wxname varchar(50),
	addon datetime,
	editon datetime,
	deleteon datetime,
	flag_delete tinyint default 0
)engine=innodb charset=utf8;

create table web_message(
	id int not null primary key auto_increment,
	user_id int not null,
	content varchar(200),
	status tinyint default 0,
	addon datetime,
	editon datetime,
	deleteon datetime,
	flag_delete tinyint default 0
)engine=innodb charset=utf8;

create table web_files
(
	id int not null primary key auto_increment,
	out_id int not null,
	file_name varchar(255),
	file_type varchar(20),
	file_path varchar(255),
	addon datetime,
	editon datetime,
	deleteon datetime,
	flag_delete tinyint default 0
	
)engine=innodb charset=utf8;

alter table sys_user drop role_id;
alter table sys_user add salt varchar(10) after phone;



















