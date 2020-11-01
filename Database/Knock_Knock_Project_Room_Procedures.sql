
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Bảng Room
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------

use knock_knock_project;

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

Call spRoom_GetRoom_ByID(5);

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

call spRoom_GetRoomList_ByID_Person(2);

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Insert

DROP PROCEDURE IF EXISTS spRoom_InsertRoom;
delimiter //
CREATE PROCEDURE spRoom_InsertRoom(in `name` varchar(20), avatar VARCHAR(128))
begin
insert into room (`name`, avatar) 
values (`name`, avatar);
end//
delimiter;

call spRoom_InsertRoom('hello' , 'a');

-- select * from room;

3. Sửa

spRoom_UpdateRoom(id, roomname)

Chỉ sửa tên phòng theo id phòng truyền vào

use knock_knock_project;

DROP PROCEDURE IF EXISTS spRoom_UpdateRoom;

delimiter //

CREATE PROCEDURE spRoom_UpdateRoom(in id int,in roomname varchar(20))

begin

update room set room.roomname = roomname where room.id=id;

end//

delimiter ;

4. Xóa

spRoom_DeleteRoom(id)

Xóa phòng theo id phòng

DROP PROCEDURE IF EXISTS spRoom_DeleteRoom;

delimiter //

CREATE PROCEDURE spRoom_DeleteRoom(in id int)

begin

delete from room where room.id = id;

end//

delimiter ;