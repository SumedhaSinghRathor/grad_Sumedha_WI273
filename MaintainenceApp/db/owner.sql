create table owner (
    owner_id int primary key,
    owner_name varchar(20) not null,
    owner_no varchar(10) not null
);

create table owner_update_request (
    request_id int primary key,
    owner_id int,
    site_id int,
    requested_type varchar(15),
    status varchar(10) check (
        status in ('Pending', 'Approved', 'Rejected')
    ) not null,
    foreign key (owner_id) references owner(owner_id),
    foreign key (site_id) references sites(site_id)
);
