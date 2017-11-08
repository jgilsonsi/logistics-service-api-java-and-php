CREATE DATABASE IF NOT EXISTS logistics_service;
 
USE logistics_service;
 
CREATE TABLE IF NOT EXISTS users(
   id int not null auto_increment primary key,
   username varchar(255) not null unique,
   password varchar(255) not null
);

CREATE TABLE IF NOT EXISTS delivery(
   id int not null auto_increment primary key,
   _order int not null unique,
   receiver_name varchar(255),
   receiver_cpf varchar(50),
   date_time timestamp null,
   user_id int not null,
   FOREIGN KEY fk_user(user_id)
   REFERENCES users(id)
   ON UPDATE CASCADE
   ON DELETE RESTRICT
);

INSERT INTO users (username, password) VALUES ('jgilson', '$2y$10$GdbmzrWDSsyc73rbn1MvkOAk/2VQAUuKHSJNsaq6kakMi9bBgZ9qi');

INSERT INTO delivery (_order, user_id) VALUES (222, 1);