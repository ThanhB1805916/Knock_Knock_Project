﻿-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Bảng Person
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
USE Knock_Knock_Project;
GO

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Get

-- Lấy ra người dùng theo @id
CREATE PROCEDURE spPerson_GetPerson_ByID(@id INT)
AS BEGIN
	SELECT * FROM Person AS p
    WHERE p.id = @id;
END;
GO

EXEC spPerson_GetPerson_ByID 1;
GO

-- Lấy ra người dùng theo tài khoản
CREATE PROCEDURE spPerson_GetPerson_ByUsername(@username VARCHAR(20))
AS 
BEGIN
	SELECT * FROM Person AS p
    WHERE p.username = @username;
END; 
GO

EXEC spPerson_GetPerson_ByUsername 'admin';
GO

-- Lấy ra người dùng theo số điện thoại
CREATE PROCEDURE spPerson_GetPerson_ByPhonenumber(@phonenumber VARCHAR(20))
AS BEGIN
	SELECT * FROM Person AS p
    WHERE p.phonenumber = @phonenumber;
END; 
GO

EXEC spPerson_GetPerson_ByPhonenumber '0000000001';
GO

-- Lấy ra theo @id phòng
CREATE PROCEDURE spPerson_GetPersonList_ByID_Room(@id_room INT)
AS BEGIN
	SELECT p.* FROM Person AS p, Person_Room AS pr
    WHERE p.id = pr.id_person
    AND pr.id_room = @id_room
    AND pr.intime >= pr.outtime;
END; 
GO

EXEC spPerson_GetPersonList_ByID_Room '1';
GO

-- Lấy ra theo @id bạn
CREATE PROCEDURE spPerson_GetPersonList_ByID_Friend(@id_friend INT)
AS BEGIN
	SELECT p.* FROM Person AS p, Friend AS f
    WHERE p.id = f.id_person
    AND f.id_friend = @id_friend;
END; 
GO

EXEC spPerson_GetPersonList_ByID_Friend '2';
GO
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Insert

-- Thủ tục thêm người dùng vào CSDL
CREATE PROCEDURE spPerson_InsertPerson(
    @username VARCHAR(20), 
    @password VARCHAR(128),
    @name NVARCHAR(128),
    @gender BIT, 
    @phonenumber CHAR(10), 
    @dateofbirth DATE,
	@avatar VARCHAR(128)
)
AS BEGIN
	INSERT INTO Person(username, [password], [name],
						gender, phonenumber, dateofbirth, avatar)
	VALUES(@username, @password, @name, 
			@gender, @phonenumber, @dateofbirth, @avatar);
END; 
GO

--EXEC spPerson_InsertPerson 'user45', '1234', 'User15', '0', '20', null, null;
-- GO;
GO

-- Thêm bạn

CREATE PROCEDURE spPerson_AddFriend (@id_person INT, @id_friend INT)
AS BEGIN
	INSERT INTO Friend(id_person, id_friend) VALUES(@id_person, @id_friend);
    INSERT INTO Friend(id_person, id_friend) VALUES(@id_friend, @id_person);
END;
GO
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Update

-- Thủ tục sửa người dùng trong CSDL theo tài khoản
CREATE PROCEDURE spPerson_UpdatePerson(
	@id INT,
	@username VARCHAR(20),
    @password VARCHAR(128),
    @name NVARCHAR(128),
    @gender BIT, 
    @phonenumber CHAR(10), 
    @dateofbirth DATE
)
AS BEGIN
	UPDATE Person SET
	username = @username,
    [password] = @password, 
    [name] = @name,
	gender = @gender, 
    phonenumber = @phonenumber, 
    dateofbirth = @dateofbirth
    WHERE id = @id;
END; 
GO

-- EXEC spPerson_UpdatePerson 'user1', '1234', 'User ONE' ,'0', '123456', '2020-10-22';
-- GO

SELECT * FROM Person;
GO
-- Xóa bạn bè
CREATE PROCEDURE spPerson_RemoveFriend(
	@id_person INT, 
	@id_friend INT
)
AS
BEGIN
    DELETE FROM Friend
    WHERE id_person = @id_person AND id_friend = @id_friend 
    OR id_person = @id_friend AND id_friend = @id_person;
END;
GO