create table mars
(
    id uuid not null primary key,
    x int not null,
    y int not null,
    obstacle char not null
);

create table rover
(
    id uuid not null primary key,
    x int not null,
    y int not null,
    direction varchar(1) not null
);