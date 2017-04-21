-- TABLE
CREATE TABLE OWNERS (
  ID                  INT NOT NULL PRIMARY KEY,
  FIRST_NAME          VARCHAR2(30),
  LAST_NAME           VARCHAR2(30),
  ADDRESS             VARCHAR(255),
  CITY                VARCHAR(80),
  TELEPHONE           VARCHAR(20),
  PROFILE_DESCRIPTION VARCHAR(255)
);

CREATE INDEX OWNERS_LAST_NAME ON OWNERS (LAST_NAME);

-- SEQUENCE
CREATE SEQUENCE OWNERS_SEQ START WITH 1;

-- INSERTS
INSERT INTO OWNERS
VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', NULL);
INSERT INTO OWNERS
VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', NULL);
INSERT INTO OWNERS
VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', NULL);
INSERT INTO OWNERS
VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', NULL);
INSERT INTO OWNERS
VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', NULL);
INSERT INTO OWNERS
VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', NULL);
INSERT INTO OWNERS
VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', NULL);
INSERT INTO OWNERS
VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', NULL);
INSERT INTO OWNERS
VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', NULL);
INSERT INTO OWNERS
VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', NULL);

-- PACKAGE HEADER
CREATE OR REPLACE PACKAGE PKG_OWNERS
  AS
  TYPE C_CURSOR IS REF CURSOR;

  PROCEDURE SP_FIND_BY_LAST_NAME(P_CURSOR    OUT C_CURSOR,
                                 P_LAST_NAME IN  OWNERS.LAST_NAME % TYPE);

  PROCEDURE SP_FIND_BY_ID(P_CURSOR OUT C_CURSOR,
                          P_ID     IN  OWNERS.ID % TYPE);

  PROCEDURE SP_INSERT(P_ID                  OUT OWNERS.ID % TYPE,
                      P_FIRST_NAME          IN  OWNERS.FIRST_NAME % TYPE,
                      P_LAST_NAME           IN  OWNERS.LAST_NAME % TYPE,
                      P_ADDRESS             IN  OWNERS.ADDRESS % TYPE,
                      P_CITY                IN  OWNERS.CITY % TYPE,
                      P_TELEPHONE           IN  OWNERS.TELEPHONE % TYPE,
                      P_PROFILE_DESCRIPTION IN  OWNERS.PROFILE_DESCRIPTION % TYPE);

  PROCEDURE SP_UPDATE(P_ID                  IN OWNERS.ID % TYPE,
                      P_FIRST_NAME          IN OWNERS.FIRST_NAME % TYPE,
                      P_LAST_NAME           IN OWNERS.LAST_NAME % TYPE,
                      P_ADDRESS             IN OWNERS.ADDRESS % TYPE,
                      P_CITY                IN OWNERS.CITY % TYPE,
                      P_TELEPHONE           IN OWNERS.TELEPHONE % TYPE,
                      P_PROFILE_DESCRIPTION IN OWNERS.PROFILE_DESCRIPTION % TYPE);

END PKG_OWNERS;

-- PACKAGE BODY
CREATE OR REPLACE PACKAGE BODY PKG_OWNERS
  AS

  PROCEDURE SP_FIND_BY_LAST_NAME(P_CURSOR    OUT C_CURSOR,
                                 P_LAST_NAME IN  OWNERS.LAST_NAME % TYPE)
    AS
    BEGIN
      OPEN P_CURSOR
      FOR
      SELECT ID,
             FIRST_NAME,
             LAST_NAME,
             ADDRESS,
             CITY,
             TELEPHONE,
             PROFILE_DESCRIPTION
        FROM OWNERS
        WHERE LAST_NAME LIKE P_LAST_NAME || '%'
        ORDER BY ID ASC;
    END SP_FIND_BY_LAST_NAME;

  PROCEDURE SP_FIND_BY_ID(P_CURSOR OUT C_CURSOR,
                          P_ID     IN  OWNERS.ID % TYPE)
    AS
    BEGIN
      OPEN P_CURSOR
      FOR
      SELECT ID,
             FIRST_NAME,
             LAST_NAME,
             ADDRESS,
             CITY,
             TELEPHONE,
             PROFILE_DESCRIPTION
        FROM OWNERS
        WHERE ID = P_ID;
    END SP_FIND_BY_ID;

  PROCEDURE SP_INSERT(P_ID                  OUT OWNERS.ID % TYPE,
                      P_FIRST_NAME          IN  OWNERS.FIRST_NAME % TYPE,
                      P_LAST_NAME           IN  OWNERS.LAST_NAME % TYPE,
                      P_ADDRESS             IN  OWNERS.ADDRESS % TYPE,
                      P_CITY                IN  OWNERS.CITY % TYPE,
                      P_TELEPHONE           IN  OWNERS.TELEPHONE % TYPE,
                      P_PROFILE_DESCRIPTION IN  OWNERS.PROFILE_DESCRIPTION % TYPE)
    AS
    BEGIN
      SELECT NVL(MAX(ID), 0) + 1
        INTO P_ID
        FROM OWNERS;
      INSERT INTO OWNERS (
        ID, FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE, PROFILE_DESCRIPTION
      )
      VALUES (P_ID, P_FIRST_NAME, P_LAST_NAME, P_ADDRESS, P_CITY, P_TELEPHONE, P_PROFILE_DESCRIPTION);
    END SP_INSERT;

  PROCEDURE SP_UPDATE(P_ID                  IN OWNERS.ID % TYPE,
                      P_FIRST_NAME          IN OWNERS.FIRST_NAME % TYPE,
                      P_LAST_NAME           IN OWNERS.LAST_NAME % TYPE,
                      P_ADDRESS             IN OWNERS.ADDRESS % TYPE,
                      P_CITY                IN OWNERS.CITY % TYPE,
                      P_TELEPHONE           IN OWNERS.TELEPHONE % TYPE,
                      P_PROFILE_DESCRIPTION IN OWNERS.PROFILE_DESCRIPTION % TYPE)
    AS
    BEGIN
      UPDATE OWNERS
        SET FIRST_NAME = P_FIRST_NAME, LAST_NAME = P_LAST_NAME, ADDRESS = P_ADDRESS, CITY = P_CITY, TELEPHONE = P_TELEPHONE, PROFILE_DESCRIPTION = P_PROFILE_DESCRIPTION
        WHERE ID = P_ID;
    END SP_UPDATE;

END PKG_OWNERS;
