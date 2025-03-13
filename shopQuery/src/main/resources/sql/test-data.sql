alter table if exists sale_option
drop constraint if exists FKd2dd11advd81ln5xo6qly8dmh;

drop table if exists cart cascade;

drop table if exists member cascade;

drop table if exists orders cascade;

drop table if exists sale_option cascade;

drop table if exists shop_page cascade;

create table cart (
    id bigint generated by default as identity,
    member_id bigint,
    option_quantities jsonb,
    primary key (id),
    unique (member_id)
);

create table member (
    brn bigint,
    created_at timestamp(6),
    id bigint generated by default as identity,
    updated_at timestamp(6),
    name varchar(255),
    phone varchar(255),
    role varchar(255) check (
        role in ('BASIC', 'BUSINESS', 'ADMIN')
    ),
    status varchar(255) check (
        status in (
            'ACTIVE',
            'INACTIVE',
            'PAUSED',
            'BANNED'
        )
    ),
    primary key (id),
    unique (brn)
);

create table orders (
    check_sum bigint,
    created_at timestamp(6),
    member_id bigint,
    order_number bigint not null,
    price bigint,
    shop_page_id bigint,
    updated_at timestamp(6),
    status varchar(255) check (
        status in (
            'IN_PAYMENT',
            'PAID',
            'CANCELED',
            'REFUNDED'
        )
    ),
    option_quantities jsonb,
    primary key (order_number)
);

create table sale_option (
    price integer,
    created_at timestamp(6),
    id bigint generated by default as identity,
    shop_page_id bigint not null,
    description varchar(255),
    name varchar(255),
    status varchar(255) check (
        status in (
            'ACTIVE',
            'INACTIVE',
            'SOLD_OUT'
        )
    ),
    primary key (id)
);

create table shop_page (
    created_at timestamp(6),
    id bigint generated by default as identity,
    member_id bigint,
    updated_at timestamp(6),
    description varchar(255),
    status varchar(255) check (
        status in (
            'ACTIVE',
            'INACTIVE',
            'SOLD_OUT'
        )
    ),
    title varchar(255),
    location geometry (Point, 4326) not null,
    thumbnail_uri bytea,
    primary key (id),
    unique (title)
);

alter table if exists sale_option
add constraint FKd2dd11advd81ln5xo6qly8dmh foreign key (shop_page_id) references shop_page;

INSERT INTO shop_page (member_id, title, description, location, status)
SELECT 
    i AS member_id,
    'Shop_' || i AS title,
    'DESCRIPTION_' || i AS description,
    ST_GeomFromText('POINT(' || (random() * 360 - 180) || ' ' || (random() * 180 - 90) || ')', 4326) AS location,
    (array['ACTIVE', 'INACTIVE', 'SOLD_OUT'])[floor(random() * 3 + 1)] AS status
FROM generate_series(1, 700000) AS i;

INSERT INTO sale_option (name, shop_page_id, description, status, price)
SELECT 
    'Option_' || i AS name,
    floor (random() * 700000) + 1 AS shop_page_id,
    'DESCRIPTION_' || i AS description,
    (array['ACTIVE', 'INACTIVE', 'SOLD_OUT'])[floor(random() * 3 + 1)] AS status,
    (random() * 1000000) AS price
FROM generate_series(1, 1500000) AS i;

INSERT INTO member (brn, name, phone, status, role)
SELECT 
    i AS brn,
    'NAME_' || (random() * 1000000 + 1) AS name,
    'PHONE_' || (random() * 1000000 + 1) AS phone,
    (array['ACTIVE', 'INACTIVE', 'PAUSED', 'BANNED'])[floor(random() * 4 + 1)] AS status,
    (array['BASIC', 'BUSINESS', 'ADMIN'])[floor(random() * 3 + 1)] AS role
FROM generate_series(1, 1000000) AS i;