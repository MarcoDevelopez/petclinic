-- TABLE
CREATE TABLE PETS (
  ID                  INT NOT NULL PRIMARY KEY,
  NAME                VARCHAR2(30),
  BIRTH_DATE           DATE,
  TYPE_ID             INT,
  OWNER_ID            INT,
  PROFILE_DESCRIPTION VARCHAR(255)
);

ALTER TABLE PETS ADD CONSTRAINT FK_PETS_OWNERS FOREIGN KEY (OWNER_ID) REFERENCES OWNERS (ID);
/* ALTER TABLE PETS ADD CONSTRAINT fk_pets_types FOREIGN KEY (TYPE_ID) REFERENCES types (ID); */
CREATE INDEX PETS_NAME ON PETS (NAME);

-- SEQUENCE
CREATE SEQUENCE PETS_SEQ START WITH 1;

-- INSERTS
INSERT INTO PETS
VALUES (1, 'Leo', TO_DATE('07/09/2010','dd/MM/yyyy'), 1, 1, NULL);
INSERT INTO PETS
VALUES (2, 'Basil', TO_DATE('06/08/2012','dd/MM/yyyy'), 6, 2, NULL);
INSERT INTO PETS
VALUES (3, 'Rosy', TO_DATE('17/04/2011','dd/MM/yyyy'), 2, 3, NULL);
INSERT INTO PETS
VALUES (4, 'Jewel', to_date('07/03/2011','dd/MM/yyyy'), 2, 3, NULL);
INSERT INTO PETS
VALUES (5, 'Iggy', to_date('30/11/2010','dd/MM/yyyy'), 3, 4, NULL);
INSERT INTO PETS
VALUES (6, 'George', TO_DATE('20/01/2010','dd/MM/yyyy'), 4, 5, NULL);
INSERT INTO PETS
VALUES (7, 'Samantha', TO_DATE('04/09/2012','dd/MM/yyyy'), 1, 6, NULL);
INSERT INTO PETS
VALUES (8, 'Max', TO_DATE('04/09/2012','dd/MM/yyyy'), 1, 6, NULL);
INSERT INTO PETS
VALUES (9, 'Lucky', TO_DATE('06/08/2011','dd/MM/yyyy'), 5, 7, NULL);
INSERT INTO PETS
VALUES (10, 'Mulligan', TO_DATE('24/02/2007','dd/MM/yyyy'), 2, 8, NULL);
INSERT INTO PETS
VALUES (11, 'Freddy', TO_DATE('09/03/2010','dd/MM/yyyy'), 5, 9, NULL);
INSERT INTO PETS
VALUES (12, 'Lucky', TO_DATE('24/06/2010','dd/MM/yyyy'), 2, 10, NULL);
INSERT INTO PETS
VALUES (13, 'Sly', TO_DATE('08/06/2012','dd/MM/yyyy'), 1, 10, NULL);

-- PACKAGE HEADER
CREATE OR REPLACE PACKAGE PKG_PETS
  AS
  TYPE C_CURSOR IS REF CURSOR;

  PROCEDURE SP_FIND_BY_ID(P_CURSOR OUT C_CURSOR,
                          P_ID     IN  PETS.ID % TYPE);

  PROCEDURE SP_FIND_ALL(P_CURSOR OUT C_CURSOR);

  PROCEDURE SP_FIND_BY_NAME(P_CURSOR OUT C_CURSOR,
                            P_NAME   IN  PETS.NAME % TYPE);

  PROCEDURE SP_INSERT(P_ID                  OUT PETS.ID % TYPE,
                      P_NAME                IN  PETS.NAME % TYPE,
                      P_BIRTH_DATE          IN  PETS.BIRTH_DATE % TYPE,
                      P_TYPE_ID             IN  PETS.TYPE_ID % TYPE,
                      P_OWNER_ID            IN  PETS.OWNER_ID % TYPE,
                      P_PROFILE_DESCRIPTION IN  PETS.PROFILE_DESCRIPTION % TYPE);

  PROCEDURE SP_UPDATE(P_ID                  IN PETS.ID % TYPE,
                      P_NAME                IN PETS.NAME % TYPE,
                      P_BIRTH_DATE          IN PETS.BIRTH_DATE % TYPE,
                      P_TYPE_ID             IN PETS.TYPE_ID % TYPE,
                      P_OWNER_ID            IN PETS.OWNER_ID % TYPE,
                      P_PROFILE_DESCRIPTION IN PETS.PROFILE_DESCRIPTION % TYPE);

END PKG_PETS;

-- PACKAGE BODY
CREATE OR REPLACE PACKAGE BODY PKG_PETS
  AS

  PROCEDURE SP_FIND_BY_ID(P_CURSOR OUT C_CURSOR,
                          P_ID     IN  PETS.ID % TYPE)
    AS
    BEGIN
      OPEN P_CURSOR
      FOR
      SELECT ID,
             NAME,
             BIRTH_DATE,
             TYPE_ID,
             OWNER_ID,
             PROFILE_DESCRIPTION
        FROM PETS
        WHERE ID = P_ID;
    END SP_FIND_BY_ID;

  PROCEDURE SP_FIND_ALL(P_CURSOR OUT C_CURSOR)
    AS
    BEGIN
      OPEN P_CURSOR
      FOR
      SELECT ID,
             NAME,
             BIRTH_DATE,
             TYPE_ID,
             OWNER_ID,
             PROFILE_DESCRIPTION
        FROM PETS
        ORDER BY NAME ASC;
    END SP_FIND_ALL;

  PROCEDURE SP_FIND_BY_NAME(P_CURSOR OUT C_CURSOR,
                            P_NAME   IN  PETS.NAME % TYPE)
    AS
    BEGIN
      OPEN P_CURSOR
      FOR
      SELECT ID,
             NAME,
             BIRTH_DATE,
             TYPE_ID,
             OWNER_ID,
             PROFILE_DESCRIPTION
        FROM PETS
        WHERE UPPER(NAME) LIKE UPPER('%' || P_NAME || '%')
        ORDER BY NAME ASC;
    END SP_FIND_BY_NAME;

  PROCEDURE SP_INSERT(P_ID                  OUT PETS.ID % TYPE,
                      P_NAME                IN  PETS.NAME % TYPE,
                      P_BIRTH_DATE          IN  PETS.BIRTH_DATE % TYPE,
                      P_TYPE_ID             IN  PETS.TYPE_ID % TYPE,
                      P_OWNER_ID            IN  PETS.OWNER_ID % TYPE,
                      P_PROFILE_DESCRIPTION IN  PETS.PROFILE_DESCRIPTION % TYPE)
    AS
    BEGIN
      SELECT PETS_SEQ.NEXTVAL
        INTO P_ID
        FROM DUAL;
      INSERT INTO PETS (
        ID, NAME, BIRTH_DATE, TYPE_ID, OWNER_ID, PROFILE_DESCRIPTION
      )
      VALUES (P_ID, P_NAME, P_BIRTH_DATE, P_TYPE_ID, P_OWNER_ID, P_PROFILE_DESCRIPTION);
    END SP_INSERT;

  PROCEDURE SP_UPDATE(P_ID                  IN PETS.ID % TYPE,
                      P_NAME                IN PETS.NAME % TYPE,
                      P_BIRTH_DATE          IN PETS.BIRTH_DATE % TYPE,
                      P_TYPE_ID             IN PETS.TYPE_ID % TYPE,
                      P_OWNER_ID            IN PETS.OWNER_ID % TYPE,
                      P_PROFILE_DESCRIPTION IN PETS.PROFILE_DESCRIPTION % TYPE)
    AS
    BEGIN
      UPDATE PETS
        SET NAME = P_NAME, BIRTH_DATE = P_BIRTH_DATE, TYPE_ID = P_TYPE_ID, OWNER_ID = P_OWNER_ID, PROFILE_DESCRIPTION = P_PROFILE_DESCRIPTION
        WHERE ID = P_ID;
    END SP_UPDATE;

END PKG_PETS;