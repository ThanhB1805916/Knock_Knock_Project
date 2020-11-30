
CREATE OR REPLACE PROCEDURE spPerson_RemoveFriend(id_person INT, id_friend INT)
AS
BEGIN
    DELETE FROM Friend f
    WHERE f.id_person = id_person AND f.id_friend = id_friend 
    OR f.id_person = id_friend AND f.id_friend = id_person;
END;

EXEC spPerson_RemoveFriend(1, 2);

CREATE OR REPLACE PROCEDURE spMessage_DeleteMessage(id int)
AS
begin
delete from message where message."id" = id;
end;

EXEC spMessage_DeleteMessage(1);


CREATE OR REPLACE FUNCTION spPerson_GetPopulation
RETURN NUMBER
IS
    cnt NUMBER(13,0);
BEGIN
   SELECT COUNT(*) INTO cnt FROM Person;
   RETURN cnt;
END;

SELECT spPerson_GetPopulation FROM DUAL;


CREATE OR REPLACE FUNCTION spRoom_GetRoomSize
RETURN NUMBER
IS
    cnt NUMBER(13,0);
BEGIN
   SELECT COUNT(*) INTO cnt FROM Room;
   RETURN cnt;
END;

SELECT spRoom_GetRoomSize FROM DUAL;

CREATE TABLE Person_Password_Log
(
    "id" NUMBER(13,0), 
    new_password VARCHAR(128) NOT NULL, 
    old_password VARCHAR(128) NOT NULL,
    modify_user VARCHAR2(100),
    modify_time TIMESTAMP DEFAULT SYSDATE
);

CREATE TABLE Person_Name_Log
(
    "id" NUMBER(13,0),
    new_name VARCHAR(128) NOT NULL, 
    old_name VARCHAR(128) NOT NULL,
    modify_user VARCHAR2(100),
    modify_time TIMESTAMP DEFAULT SYSDATE
);

CREATE OR REPLACE TRIGGER Person_Before_Update_Password
BEFORE UPDATE OF "password" ON Person
FOR EACH ROW
BEGIN
   INSERT INTO PERSON_Password_Log("id", new_password, old_password, modify_user) VALUES(:OLD."id", :NEW."password", :OLD."password", user);
END;

CREATE OR REPLACE TRIGGER Person_Before_Update_Name
BEFORE UPDATE OF "name" ON Person
FOR EACH ROW
BEGIN
   INSERT INTO PERSON_Name_Log("id", new_name, old_name, modify_user) VALUES(:OLD."id", :NEW."name", :OLD."name", user);
END;

