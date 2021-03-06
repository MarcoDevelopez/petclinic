-- TABLE: USERS
CREATE TABLE USERS (
  ID                    INT NOT NULL PRIMARY KEY,
  EMAIL                 VARCHAR2(50),
  ENABLED               CHAR(1),
  FIRSTNAME             VARCHAR2(50),
  LASTNAME              VARCHAR2(50),
  LASTPASSWORDRESETDATE TIMESTAMP,
  USERNAME              VARCHAR2(50),
  PASSWORD              VARCHAR2(60)
);

CREATE INDEX USERS_USERNAME ON USERS (USERNAME);

-- INSERTS
INSERT INTO USERS (
  ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE
)
VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, TO_DATE('01-01-2016', 'dd/MM/yyyy'));
INSERT INTO USERS (
  ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE
)
VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, TO_DATE('01-01-2016', 'dd/MM/yyyy'));
INSERT INTO USERS (
  ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE
)
VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, TO_DATE('01-01-2016', 'dd/MM/yyyy'));

-- PACKAGE HEADER
CREATE OR REPLACE PACKAGE PKG_USERS
  AS
  TYPE C_CURSOR IS REF CURSOR;

  PROCEDURE SP_FIND_BY_USERNAME(P_CURSOR   OUT C_CURSOR,
                                P_USERNAME IN  USERS.USERNAME % TYPE);

END PKG_USERS;

-- PACKAGE BODY
CREATE OR REPLACE PACKAGE BODY PKG_USERS
  AS

  PROCEDURE SP_FIND_BY_USERNAME(P_CURSOR   OUT C_CURSOR,
                                P_USERNAME IN  USERS.USERNAME % TYPE)
    AS
    BEGIN
      OPEN P_CURSOR
      FOR
      SELECT ID,
             EMAIL,
             ENABLED,
             FIRSTNAME,
             LASTNAME,
             LASTPASSWORDRESETDATE,
             USERNAME,
             PASSWORD
        FROM USERS
        WHERE UPPER(USERNAME) LIKE UPPER(P_USERNAME);
    END SP_FIND_BY_USERNAME;

END PKG_USERS;


-- TABLE: AUTHORITIES
CREATE TABLE AUTHORITIES (
  ID   NUMBER NOT NULL PRIMARY KEY,
  NAME VARCHAR2(50)
);

-- INSERTS
INSERT INTO AUTHORITIES (
  ID, NAME
)
VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITIES (
  ID, NAME
)
VALUES (2, 'ROLE_ADMIN');


-- TABLE: USER_AUTHORITIES
CREATE TABLE USER_AUTHORITIES (
  USER_ID      INT,
  AUTHORITY_ID INT
);

-- INSERTS
INSERT INTO USER_AUTHORITIES (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITIES (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITIES (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITIES (USER_ID, AUTHORITY_ID) VALUES (3, 1);
