-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Bảng Person
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------

USE Knock_Knock_Project;

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Get

-- Lấy ra người dùng theo id
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_GetPerson_ByID $$
CREATE PROCEDURE spPerson_GetPerson_ByID(id INT)
BEGIN
	SELECT * FROM Person AS p
    WHERE id = p.id;
END; $$

CALL spPerson_GetPerson_ByID('1');

-- Lấy ra người dùng theo tài khoản
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_GetPerson_ByUsername $$
CREATE PROCEDURE spPerson_GetPerson_ByUsername(username VARCHAR(20))
BEGIN
	SELECT * FROM Person AS p
    WHERE username = p.username;
END; $$

CALL spPerson_GetPerson_ByAccountAndPassword('admin');

-- Lấy ra người dùng theo số điện thoại
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_GetPerson_ByPhonenumber $$
CREATE PROCEDURE spPerson_GetPerson_ByPhonenumber(phonenumber VARCHAR(20))
BEGIN
	SELECT * FROM Person AS p
    WHERE phonenumber = p.phonenumber;
END; $$

CALL spPerson_GetPerson_ByAccountAndPassword('0000000001');

-- Lấy ra người dùng theo id phòng
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_GetPersonList_ByID_Room $$
CREATE PROCEDURE spPerson_GetPersonList_ByID_Room(id_room INT)
BEGIN
	SELECT p.* FROM Person AS p, Person_Room AS pr
    WHERE p.id = pr.id_person
    AND pr.id_room = id_room;
END; $$

CALL spPerson_GetPersonList_ByID_Room('2');

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Insert

-- Thủ tục thêm người dùng vào CSDL
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_InsertPerson $$
CREATE PROCEDURE spPerson_InsertPerson(
    username VARCHAR(20), 
    `password` VARCHAR(128),
    `name` NVARCHAR(128),
    gender BOOL, 
    phonenumber CHAR(10), 
    dateofbirth DATE
)
BEGIN
	INSERT INTO Person(username, `password`, `name`,
						gender, phonenumber, dateofbirth)
	VALUES(username, `password`, `name`, 
			gender, phonenumber, dateofbirth);
END; $$

CALL spPerson_InsertPerson('user45', '1234', 'User15', '100', '20', null);

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Update

-- Thủ tục sửa người dùng trong CSDL theo tài khoản
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_UpdatePerson $$
CREATE PROCEDURE spPerson_UpdatePerson(
	username VARCHAR(20),
    `password` VARCHAR(128),
    `name` NVARCHAR(128),
    gender BOOL, 
    phonenumber CHAR(10), 
    dateofbirth DATE
)
BEGIN
	UPDATE Person AS p SET
    p.`password` = `password`, 
    p.`name` = `name`,
	p.gender = gender, 
    p.phonenumber = phonenumber, 
    p.dateofbirth = dateofbirth
    WHERE p.username = username;
END; $$

-- CALL spPerson_UpdatePerson('user1', '1234', 'User ONE' ,'0', '123456', '2020-10-22');

-- SELECT * FROM Person;