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

-- CALL spPerson_GetPerson_ByID('1');

-- Lấy ra người dùng theo tài khoản
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_GetPerson_ByUsername $$
CREATE PROCEDURE spPerson_GetPerson_ByUsername(username VARCHAR(20))
BEGIN
	SELECT * FROM Person AS p
    WHERE username = p.username;
END; $$

-- CALL spPerson_GetPerson_ByAccountAndPassword('admin');
-- Lấy ra người dùng theo số điện thoại
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_GetPerson_ByPhonenumber $$
CREATE PROCEDURE spPerson_GetPerson_ByPhonenumber(phonenumber VARCHAR(20))
BEGIN
	SELECT * FROM Person AS p
    WHERE phonenumber = p.phonenumber;
END; $$

-- CALL spPerson_GetPerson_ByAccountAndPassword('0000000001');

-- Lấy ra danh sách theo id phòng
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_GetPersonList_ByID_Room $$
CREATE PROCEDURE spPerson_GetPersonList_ByID_Room(id_room INT)
BEGIN
	SELECT p.* FROM Person AS p, Person_Room AS pr
    WHERE p.id = pr.id_person
    AND pr.id_room = id_room;
END; $$

-- CALL spPerson_GetPersonList_ByID_Room('1');

-- Lấy ra danh sách theo id bạn
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_GetPersonList_ByID_Friend $$
CREATE PROCEDURE spPerson_GetPersonList_ByID_Friend(id_friend INT)
BEGIN
	SELECT p.* FROM Person AS p, Friend AS f
    WHERE p.id = f.id_person
    AND f.id_friend = id_friend;
END; $$

-- CALL  spPerson_GetPersonList_ByID_Friend(2);

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
    dateofbirth DATE,
    avatar VARCHAR(128)
)
BEGIN
	INSERT INTO Person(username, `password`, `name`,
						gender, phonenumber, dateofbirth, avatar)
	VALUES(username, `password`, `name`, 
			gender, phonenumber, dateofbirth, avatar);
END; $$

-- CALL spPerson_InsertPerson('user45', '1234', 'User15', '100', '20', null, null);

-- Thêm bạn
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_AddFriend $$
CREATE PROCEDURE spPerson_AddFriend(
   id_person INT,
   id_friend INT
)
BEGIN
	INSERT INTO Friend(id_person, id_friend) VALUES(id_person, id_friend);
    INSERT INTO Friend(id_person, id_friend) VALUES(id_friend, id_person);
END; $$

-- CALL spPerson_AddFriend(3, 4);

-- ---------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Update

-- Thủ tục sửa người dùng trong CSDL theo tài khoản
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_UpdatePerson $$
CREATE PROCEDURE spPerson_UpdatePerson(
	id INT,
	username VARCHAR(20),
    `password` VARCHAR(128),
    `name` NVARCHAR(128),
    gender BOOL, 
    phonenumber CHAR(10), 
    dateofbirth DATE,
    avatar VARCHAR(128)
)
BEGIN
	UPDATE Person AS p SET
    p.username = username,
    p.`password` = `password`, 
    p.`name` = `name`,
	p.gender = gender, 
    p.phonenumber = phonenumber, 
    p.dateofbirth = dateofbirth,
    p.avatar = avatar
    WHERE p.id = id;
END; $$
SELECT * FROM PERSON;
-- CALL spPerson_UpdatePerson(28, 'User5001', '1234', 'AAA', '0', '0123454688', '2020-11-10', 'AA');
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Remove

-- Xóa bạn bè
DELIMITER $$
DROP PROCEDURE IF EXISTS spPerson_RemoveFriend $$
CREATE PROCEDURE spPerson_RemoveFriend(id_person INT, id_friend INT)
BEGIN
    DELETE FROM Friend AS f
    WHERE f.id_person = id_person AND f.id_friend = id_friend 
    OR f.id_person = id_friend AND f.id_friend = id_person;
END; $$

-- SELECT * FROM Person AS p, Friend AS f
-- WHERE p.id = f.id_person
-- AND p.id = 1 AND f.id_friend = 2
-- OR p.id = 2 AND f.id_friend = 1;

-- CALL spPerson_RemoveFriend(2, 1);

-- CALL spPerson_UpdatePerson('user1', '1234', 'User ONE' ,'0', '123456', '2020-10-22');

-- SELECT * FROM Person;