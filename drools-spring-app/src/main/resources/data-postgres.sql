-- 123 pass
INSERT INTO USERS (username, password, name, surname, email) VALUES ('user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com');
INSERT INTO USERS (username, password, name, surname, email) VALUES ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com');
INSERT INTO USERS (username, password, name, surname, email) VALUES ('lazar', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Lazar', 'Lazic', 'laza@example.com');

INSERT INTO ADMIN (id) values (2);
-- Roles
--INSERT INTO ROLE (name) VALUES ('ROLE_PATIENT');
--INSERT INTO ROLE (name) VALUES ('ROLE_DERMATOLOGIST');
--INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');

--INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- TENANT
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 3); -- ADMIN
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 2); -- ADMIN

--INSERT INTO PRIVILEGE (name) VALUES ('CREATE_PRIVILEGE');
--INSERT INTO PRIVILEGE (name) VALUES ('READ_PRIVILEGE');
--INSERT INTO PRIVILEGE (name) VALUES ('UPDATE_PRIVILEGE');
--INSERT INTO PRIVILEGE (name) VALUES ('DELETE_PRIVILEGE');

-- Connect privilege to role
--INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (1, 2);
--INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (2, 2);
--INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (3, 1);

-- Characteristics
INSERT INTO SKIN_TYPE_CHARACTERISTICS (skin_type) VALUES ('NORMAL');

INSERT INTO SKIN_CHARACTERISTICS (skin_type_id, characteristic) VALUES (1, 'TIGHT_SKIN');

