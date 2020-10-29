
create table if not exists gift_certificate
(
    id int auto_increment
        primary key,
    name varchar(100) not null,
    description varchar(100) not null,
    price decimal(20,2) null,
    creationTime timestamp null,
    updateTime timestamp null,
    duration int null
);


create table if not exists tag
(
    id int auto_increment
        primary key,
    name varchar(50) null,
    constraint tag_name_uindex
        unique (name)
);

create table if not exists gift_to_tag
(
    cert_id int null,
    id int auto_increment
        primary key,
    tag_id int null,
    constraint cert_fk
        foreign key (cert_id) references gift_certificate (id),
    constraint tag_fk
        foreign key (tag_id) references tag (id)
            on update cascade on delete set null
);

