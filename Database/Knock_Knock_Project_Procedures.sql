
USE Knock_Knock_Project;




-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Bảng Friend
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------

-- Thủ tục lấy ra danh sách id friend của id truyền vào
DELIMITER $$
DROP PROCEDURE IF EXISTS spFriend_GetId_FriendList $$
CREATE PROCEDURE spFriend_GetId_FriendList(id_person INT)
BEGIN
	SELECT f.id_friend FROM Friend AS f
    WHERE f.id_person = id_person;
END; $$
SELECT * FROM Person;
CALL spFriend_GetId_FriendList (2);

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Bảng Room
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------

-- Thủ tục trả về danh sách phòng theo id người dùng

DELIMITER $$
DROP PROCEDURE IF EXISTS spRooms_GetRoomList_ById_Person $$
CREATE PROCEDURE spRooms_GetRoomList_ById_Person(id_person INT)
BEGIN
	SELECT r.* FROM Person_Room AS pr, Room AS r
    WHERE pr.id_room = r.id
    AND pr.id_person = id_person;
END; $$

CALL spRooms_GetRoomList_ById_Person(1);


DELIMITER $$
DROP PROCEDURE IF EXISTS spRoom_GetList $$
CREATE PROCEDURE spRoom_GetList()
BEGIN
	SELECT * FROM Room;
END; $$

CALL spRoom_GetList;




