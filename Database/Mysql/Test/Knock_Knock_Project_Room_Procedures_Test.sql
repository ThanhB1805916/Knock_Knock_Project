
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Bảng Room
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
use knock_knock_project_test;

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Get

-- Lấy ra phòng theo id phòng
DROP PROCEDURE IF EXISTS spRoom_GetRoom_ByID;
delimiter //
CREATE PROCEDURE spRoom_GetRoom_ByID (in id int)
BEGIN
SELECT * from room where room.id=id;
END//
delimiter ;

 -- Call spRoom_GetRoom_ByID(1);

-- Lấy ra danh sách phòng theo id người dùng truyền vào
DROP PROCEDURE IF EXISTS spRoom_GetRoomList_ByID_Person;
delimiter //
CREATE PROCEDURE spRoom_GetRoomList_ByID_Person (in id_person int)
BEGIN
select r.* from room as r 
join person_room as p 
on r.id = p.id_room 
where p.id_person=id_person;
END//
delimiter ;

-- call spRoom_GetRoomList_ByID_Person(1);
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Insert

-- Thêm phòng mới theo tên và avatar truyền vào

use knock_knock_project_test;

DROP PROCEDURE IF EXISTS spRoom_InsertRoom;
delimiter //
CREATE PROCEDURE spRoom_InsertRoom(in `name` varchar(20), in avatar varchar(128))
begin
insert into room (`name`, avatar) values (`name`, avatar);
end//
delimiter ;
-- call spRoom_InsertRoom('hello' , 'a');

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Update


-- Sửa tên phòng, avatar theo id phòng truyền vào

use knock_knock_project_test;
DROP PROCEDURE IF EXISTS spRoom_UpdateRoom;
delimiter //
CREATE PROCEDURE spRoom_UpdateRoom(in id int, in `name` varchar(20), in avatar varchar(128))
begin
update room 
set room.name = `name`,
	room.avatar = avatar
where room.id=id;
end//
delimiter ;
-- CALL spRoom_UpdateRoom(11, 'Phòng 11', 'avatar');
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Delete

-- Xóa phòng theo id phòng

DROP PROCEDURE IF EXISTS spRoom_DeleteRoom;
delimiter //
CREATE PROCEDURE spRoom_DeleteRoom(in id int)
begin
delete from room where room.id = id;
end//
delimiter ;

-- CALL spRoom_DeleteRoom(12);