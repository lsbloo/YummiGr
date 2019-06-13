insert into role (id, name) values
(20, 'ROLE_ADMIN'),
(21, 'ROLE_USER');

insert into privilege (id,name) values
(10,'READ_PRIVILEGE'),
(11,'WRITE_PRIVILEGE');

insert into tracy_user (id, actived, email, first_name, identifier, last_name, password, username) values
(30, true, 'administration@yummigr.org', 'Administrador', 'admin','Administrador', '$2a$10$WwnEzg0ppWn1q1qBwgnMEe5PN5m.a00VKrwtg2HRvBrcirJPK90b2','admin');


insert into usuarios_role (usuarios_id, role_id) values
(30, 20),
(30, 21);


