create table if not exists school_management.accounts
(
    id         bigint auto_increment
        primary key,
    username   varchar(255)                       not null,
    password   varchar(255)                       not null,
    status     varchar(255)                       null,
    created_at datetime default CURRENT_TIMESTAMP null,
    created_by varchar(255)                       null,
    updated_at datetime                           null on update CURRENT_TIMESTAMP,
    updated_by varchar(255)                       null,
    deleted_at datetime                           null,
    constraint account_username_idx
        unique (username)
)
    engine = InnoDB;

create table if not exists school_management.users
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
)
    engine = InnoDB;

create index users_account_id_index
    on school_management.users (account_id);