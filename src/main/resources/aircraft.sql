create schema if not exists Aircraft;
use Aircraft;
drop table if exists Aircraft;
create table Aircraft
(
    id    int auto_increment primary key,
    name  varchar(45),
    goals integer,
    team  varchar(45)
);

insert into Person (name, goals, team) values ('asdf', 10, 'jklö');
commit;