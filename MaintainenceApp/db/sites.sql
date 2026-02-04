CREATE TABLE sites (
    site_id int serial primary key,
    length_ft int,
    width_ft int,
    site_type varchar(17) check (
        site_type in ('Open', 'Villa', 'Apartment', 'Independent House')
    ) not null,
    site_status varchar(8) check (
        site_status in ('Open', 'Occupied')
    ) not null,
    owner_id int
    foreign key (owner_id) references owner(owner_id)
);

insert into sites values (1, 40, 60, 'Open', 'Open', NULL);
insert into sites values (2, 40, 60, 'Open', 'Open', NULL);
insert into sites values (3, 40, 60, 'Open', 'Open', NULL);
insert into sites values (4, 40, 60, 'Open', 'Open', NULL);
insert into sites values (5, 40, 60, 'Open', 'Open', NULL);
insert into sites values (6, 40, 60, 'Open', 'Open', NULL);
insert into sites values (7, 40, 60, 'Open', 'Open', NULL);
insert into sites values (8, 40, 60, 'Open', 'Open', NULL);
insert into sites values (9, 40, 60, 'Open', 'Open', NULL);
insert into sites values (10, 40, 60, 'Open', 'Open', NULL);
insert into sites values (11, 30, 50, 'Open', 'Open', NULL);
insert into sites values (12, 30, 50, 'Open', 'Open', NULL);
insert into sites values (13, 30, 50, 'Open', 'Open', NULL);
insert into sites values (14, 30, 50, 'Open', 'Open', NULL);
insert into sites values (15, 30, 50, 'Open', 'Open', NULL);
insert into sites values (16, 30, 50, 'Open', 'Open', NULL);
insert into sites values (17, 30, 50, 'Open', 'Open', NULL);
insert into sites values (18, 30, 50, 'Open', 'Open', NULL);
insert into sites values (19, 30, 50, 'Open', 'Open', NULL);
insert into sites values (20, 30, 50, 'Open', 'Open', NULL);
insert into sites values (21, 30, 40, 'Open', 'Open', NULL);
insert into sites values (22, 30, 40, 'Open', 'Open', NULL);
insert into sites values (23, 30, 40, 'Open', 'Open', NULL);
insert into sites values (24, 30, 40, 'Open', 'Open', NULL);
insert into sites values (25, 30, 40, 'Open', 'Open', NULL);
insert into sites values (26, 30, 40, 'Open', 'Open', NULL);
insert into sites values (27, 30, 40, 'Open', 'Open', NULL);
insert into sites values (28, 30, 40, 'Open', 'Open', NULL);
insert into sites values (29, 30, 40, 'Open', 'Open', NULL);
insert into sites values (30, 30, 40, 'Open', 'Open', NULL);
insert into sites values (31, 30, 40, 'Open', 'Open', NULL);
insert into sites values (32, 30, 40, 'Open', 'Open', NULL);
insert into sites values (33, 30, 40, 'Open', 'Open', NULL);
insert into sites values (34, 30, 40, 'Open', 'Open', NULL);
insert into sites values (35, 30, 40, 'Open', 'Open', NULL);

select * from sites
order by site_id;