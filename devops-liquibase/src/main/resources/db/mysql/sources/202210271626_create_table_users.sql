-- auto-generated definition
create table users
(
    id         bigint auto_increment
        primary key,
    first_name varchar(255)                       not null,
    last_name  varchar(255)                       not null,
    account_id bigint                             null,
    created_at datetime default CURRENT_TIMESTAMP null,
    created_by varchar(255)                       null,
    updated_at datetime                           null on update CURRENT_TIMESTAMP,
    updated_by varchar(255)                       null,
    deleted_at datetime                           null
);

create index users_account_id_index
    on users (account_id);

