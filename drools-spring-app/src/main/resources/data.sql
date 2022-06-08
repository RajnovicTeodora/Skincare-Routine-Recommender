INSERT INTO role_table (id, name) VALUES (1, 'ADMIN');
INSERT INTO role_table (id, name) VALUES (2, 'DERMATOLOGIST');
INSERT INTO role_table (id, name) VALUES (3, 'PATIENT');

-- 123 pass
INSERT INTO system_user (username, password, name, surname, email, role_id) VALUES ('user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', 3);
INSERT INTO system_user (username, password, name, surname, email, role_id) VALUES ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', 1);
INSERT INTO system_user (username, password, name, surname, email, role_id) VALUES ('lazar', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Lazar', 'Lazic', 'laza@example.com', 2);

insert into admin (id) values (2);
insert into patient (id, birthday, gender) values (1, '2020-10-31', 1);
insert into dermatologist (id) values (3);
-- Roles
--INSERT INTO ROLE (name) VALUES ('ROLE_PATIENT');
--INSERT INTO ROLE (name) VALUES ('ROLE_DERMATOLOGIST');
--INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');

--INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- TENANT
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 3); -- ADMIN
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 2); -- ADMIN


-- Characteristics
-- INSERT INTO SKIN_TYPE_CHARACTERISTICS (skin_type) VALUES ('NORMAL');

-- INSERT INTO SKIN_CHARACTERISTICS (skin_type_id, characteristic) VALUES (1, 'TIGHT_SKIN');

