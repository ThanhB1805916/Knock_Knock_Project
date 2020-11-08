
USE Knock_Knock_Project;

DELIMITER $$
DROP PROCEDURE IF EXISTS spTest_GetStatus $$
CREATE PROCEDURE spTest_GetStatus()
BEGIN
	SELECT * FROM Person;
    SELECT * FROM Friend;
    SELECT * FROM Person_Room;
    SELECT * FROM Room;
    SELECT * FROM message;
END; $$

CALL spDataBase_GetStatus;


DELIMITER $$
DROP PROCEDURE IF EXISTS spTest_Insert $$
CREATE PROCEDURE spTest_Insert()
BEGIN
	SELECT * FROM Person;
    SELECT * FROM Friend;
    SELECT * FROM Person_Room;
    SELECT * FROM Room;
    SELECT * FROM message;
END; $$
