create sequence auto_increment start with 1 increment by 50;

create table books
(
    category_id bigint,
    count_page  bigint,
    created_at  timestamp(6),
    id          uuid not null,
    user_id     uuid,
    description varchar(255),
    image_uri   varchar(255),
    title       varchar(255),
    primary key (id)
);
create table categories
(
    id          bigint not null,
    description varchar(255),
    image_uri   varchar(255),
    name        varchar(255),
    primary key (id)
);

create table authors
(
    id          uuid not null,
    description varchar(255),
    image_uri   varchar(255),
    name        varchar(255),
    primary key (id)
);

create table book_author
(
    author_id uuid not null,
    book_id   uuid not null
);



alter table if exists books
    add constraint FKleqa3hhc0uhfvurq6mil47xk0
        foreign key (category_id)
            references categories;

alter table if exists book_author
    add constraint FK91ierknt446aaqnjl4uxjyls3
        foreign key (book_id)
            references books;


alter table if exists book_author
    add constraint FKro54jqpth9cqm1899dnuu9lqg
        foreign key (author_id)
            references authors;
