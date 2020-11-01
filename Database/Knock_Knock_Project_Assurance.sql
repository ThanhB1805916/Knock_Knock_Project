
USE CT240_Message_Project;

DELIMITER $$
DROP PROCEDURE IF EXISTS spDataBase_GetStatus $$
CREATE PROCEDURE spDataBase_GetStatus()
BEGIN
	SELECT * FROM Person;
    SELECT * FROM Friend;
    SELECT * FROM Person_Room;
    SELECT * FROM Room;
    SELECT * FROM message;
END; $$

CALL spDataBase_GEtStatus;