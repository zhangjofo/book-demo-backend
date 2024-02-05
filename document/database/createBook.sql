create table if not exists book
(
    id               bigint auto_increment
        primary key,
    title            varchar(50) not null comment 'book title',
    author           varchar(50) not null comment 'book author',
    publication_year int         not null comment 'publication year',
    isbn             varchar(30) not null comment 'International Standard Book Number',
    book_status      tinyint(1)  not null comment '0 is Available,1 is loaned,2 is disabled',
    created_time     timestamp   not null on update CURRENT_TIMESTAMP,
    update_time      timestamp   null on update CURRENT_TIMESTAMP,
    constraint uk_isbn
        unique (isbn)
);