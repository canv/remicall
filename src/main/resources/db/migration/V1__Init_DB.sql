create sequence hibernate_sequence start 1 increment 1;

create table message (
    id int4 not null,
    filename varchar(255),
    tag varchar(255),
    text varchar(2048) not null,
    user_id uuid,
    primary key (id)
);

create table user_role (
    user_id uuid not null,
    roles varchar(255)
);

create table user_data (
    user_id uuid not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (user_id)
);

alter table if exists message
    add constraint message_user_fk
    foreign key (user_id) references user_data;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references user_data;