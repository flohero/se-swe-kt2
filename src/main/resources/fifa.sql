create schema if not exists Fifa;
use Fifa;
drop table if exists Person;
create table Person
(
    id    int auto_increment primary key,
    name  varchar(45),
    goals integer,
    team  varchar(45)
);

insert into Person (name, goals, team) values ('asdf', 10, 'jklö');
commit;