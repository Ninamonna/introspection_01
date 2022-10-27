create sequence hibernate_sequence start 1 increment 1;

create table note (
    id int8 not null,
    another varchar(2048) not null,
    bad varchar(2048) not null,
    date date not null,
    good varchar(2048) not null,
    target_approach varchar(2048) not null,
    together varchar(2048) not null,
    user_id int8,
    primary key (id)
    );

create table user_role (
    user_id int8 not null,
    roles varchar(255)
    );

create table usr (
    id int8 not null,
    activation_code varchar(2048),
    active boolean not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
    );

alter table if exists note
    add constraint note_user_fk
        foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;