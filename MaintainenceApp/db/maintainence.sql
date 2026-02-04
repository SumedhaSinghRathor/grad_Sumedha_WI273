create table maintenance (
    m_id int primary key,
    site_id int not null,
    m_month int check (m_month between 1 and 12) not null,
    m_year int not null,
    amount double not null,
    paid_status varchar(10) check (
        paid_status in ('Paid', 'Pending')
    ) not null,
    paid_date date,
    foreign key (site_id) references sites(site_id)
);


alter table maintenance
add constraint unique_site_month_year
unique (site_id, m_month, m_year);