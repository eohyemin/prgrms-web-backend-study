Create table users (
                       seq bigint not null auto_increment,
                       email varchar(50) not null,
                       passwd varchar(80) not null,
                       login_count int  not null default 0,
                       last_login_date datetime default null,
                       create_at datetime not null default current_timestamp(),
                       primary key (seq),
                       constraint unq_user_email unique (email)
);