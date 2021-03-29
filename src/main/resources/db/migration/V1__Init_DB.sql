create sequence hibernate_sequence start 1 increment 1;

create table user (
    user_id int8 unique not null,
    user_name varchar(32) unique not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    full_name varchar(511) not null,
    primary key(user_id)
    );

create table phonebook (
    entry_id int8 unique not null,
    alias varchar(32) not null,
    phone_number varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    full_name varchar(511) not null,
    owner int8,
    primary key(entry_id)
    );

alter table if exists phonebook
    add constraint phonebook_user_fk
    foreign key (owner) references user;