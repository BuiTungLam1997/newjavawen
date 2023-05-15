use newjavaweb;
create table role (
                      id bigint not null primary key auto_increment,
                      name varchar(255) not null,
                      code varchar(255) not null,
                      createddate timestamp,
                      createdby varchar(255),
                      modifieddate timestamp,
                      modifiedby varchar(255)
);
create table user(
                     id bigint not null primary key auto_increment,
                     username varchar(255) not null,
                     password varchar(150) not null,
                     fullname varchar(150),
                     roleid bigint not null,
                     status int not null,
                     createddate timestamp,
                     createdby varchar(255),
                     modifieddate timestamp,
                     modifiedby varchar(255)
);
alter table user add constraint fk_user_role foreign key (roleid) references role(id);

create table news(
                     id bigint not null primary key auto_increment,
                     title varchar(255) null,
                     content text null,
                     thumbnail varchar(150) null,
                     shortdescription text null,
                     categoryid bigint,
                     createddate timestamp,
                     createdby varchar(255),
                     modifieddate timestamp,
                     modifiedby varchar(255)
);
create table category (
                          id bigint not null primary key auto_increment,
                          name varchar(255) not null,
                          code varchar(255) not null,
                          createddate timestamp,
                          createdby varchar(255),
                          modifieddate timestamp,
                          modifiedby varchar(255)
);
create table comment (
                         id bigint not null primary key auto_increment,
                         userid bigint not null ,
                         newid bigint not null,
                         content text not null,
                         createddate timestamp,
                         createdby varchar(255),
                         modifieddate timestamp,
                         modifiedby varchar(255)
);
alter table news add constraint fk_news_category foreign key (categoryid) references category (id);
alter table comment add constraint fk_comment_user foreign key (userid) references user(id);
alter table comment add constraint fk_comment_new foreign key (newid) references news(id);