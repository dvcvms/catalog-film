INSERT INTO ROLES (NAME)
SELECT 'ROLE_USER' FROM dual WHERE NOT EXISTS(SELECT 1 FROM ROLES WHERE NAME='ROLE_USER');

INSERT INTO ROLES (NAME)
SELECT 'ROLE_ADMIN' FROM dual WHERE NOT EXISTS(SELECT 1 FROM ROLES WHERE NAME='ROLE_ADMIN');