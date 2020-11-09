-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Bảng Message
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------

use knock_knock_project;

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Get

-- Lấy ra tin nhắn theo id 
use knock_knock_project;
DROP PROCEDURE IF EXISTS spMessage_GetMessage_ByID;
delimiter //
CREATE PROCEDURE spMessage_GetMessage_ByID(in id int)
begin
select message.* from message where message.id = id;
end//
delimiter ;
-- CALL spMessage_GetMessage_ByID(2);
-- use knock_knock_project;
-- DROP PROCEDURE IF EXISTS spMessage_GetMessage_ByID_RoomAndID_Person;
-- delimiter //
-- CREATE PROCEDURE spMessage_GetMessage_ByID_RoomAndID_Person(in id_room int,in id_person int )
-- begin
-- select message.messagecontent from message where message.id_room=id_room and message.id_person=id_person;
-- end//
-- delimiter ;

-- CALL spMessage_GetMessage_ByID_RoomAndID_Person(1, 1);

-- Lấy ra danh sách tin nhắn theo id phòng

use knock_knock_project;
DROP PROCEDURE IF EXISTS spMessage_GetMessageList_ByID_Room;
delimiter //
CREATE PROCEDURE spMessage_GetMessageList_ByID_Room(in id_room int )
begin
select message.* from message where message.id_room=id_room;
end//
delimiter ;

-- CALL spMessage_GetMessageList_ByID_Room (1);

-- ---------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Insert

-- Thêm tin nhắn mới vào phòng
use knock_knock_project;
DROP PROCEDURE IF EXISTS spMessage_InsertMessage;
delimiter //
CREATE PROCEDURE spMessage_InsertMessage(in id_room int, in id_person int, in messagecontent varchar(128), isFile boolean, sendtime DATETIME)
begin
insert into message(id_room, id_person, messagecontent, isFile, sendtime) values (id_room,id_person,messagecontent, isFile, sendtime);
end//
delimiter ;

-- CALL spMessage_InsertMessage(1, 1, 'Ola', true, now());

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Update
-- Sửa tin nhắn
use knock_knock_project;
DROP PROCEDURE IF EXISTS spMessage_UpdateMessage;
delimiter //
CREATE PROCEDURE spMessage_UpdateMessage(in id int, isFile boolean, in messagecontent varchar(128))
begin
update message 
set message.messagecontent = messagecontent,
	message.isFile = isFile
where message.id = id;
end//
delimiter ;

-- CALL spMessage_UpdateMessage('1', false, 'Xin chào');

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Delete

-- Xóa tin nhắn theo id
use knock_knock_project;
DROP PROCEDURE IF EXISTS spMessage_DeleteMessage;
delimiter //
CREATE PROCEDURE spMessage_DeleteMessage(in id int)
begin
delete from message where message.id = id;
end//
delimiter ;

-- CALL spMessage_DeleteMessage(1);
