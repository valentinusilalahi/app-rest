create table customer (
	id varchar(36),
	customer_number varchar(100) not null,
	name varchar(255) not null,
	email varchar(100) not null,
	mobile_phone varchar(100) not null,
	primary key(id),
	unique (customer_number),
	unique (email)
);

create table account(
	id varchar(36), 
    id_customer varchar(36) not null,
    account_number varchar(100) not null,
    name varchar(255) not null,
    balance numeric(19,2) not null,
    primary key (id), 
    unique (account_number), 
    unique (name), 
    foreign key (id_customer) references customer(id)
);

create table account_transaction (
    id varchar(36), 
    id_account varchar(36) not null,
    transaction_time timestamp not null, 
    transaction_type varchar(50) not null,
    amount numeric(19,2) not null,
    description varchar(255),
    primary key (id), 
    foreign key (id_account) references account(id)
)
