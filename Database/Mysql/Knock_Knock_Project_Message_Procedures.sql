-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Bảng Message
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------

use knock_knock_project;

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Get

-- Lấy ra tin nhắn theo id room và id person

use knock_knock_project;

DROP PROCEDURE IF EXISTS spMessage_GetMessage_ByID_RoomAndID_Person;

delimiter //

CREATE PROCEDURE spMessage_GetMessage_ByID_RoomAndID_Person(in id_room int,in id_person int )

begin

select message.messagecontent from message where message.id_room=id_room and message.id_person=id_person;

end//

delimiter ;



-- Lấy ra danh sách tin nhắn theo id phòng

use knock_knock_project;

DROP PROCEDURE IF EXISTS spMessage_GetMessageList_ByID_Room;

delimiter //

CREATE PROCEDURE spMessage_GetMessageList_ByID_Room(in id_room int )

begin

select message.messagecontent from message where message.id_room=id_room;

end//

delimiter ;


-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Insert

-- Thêm tin nhắn mới vào phòng

use knock_knock_project;

DROP PROCEDURE IF EXISTS spMessage_InsertMessage;

delimiter //

CREATE PROCEDURE spMessage_InsertMessage(in id_room int, in id_person int, in messagecontent varchar(128))

begin

insert into message(id_room, id_person, messagecontent) values (id_room,id_person,messagecontent);

end//

delimiter ;

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Update

-- Sửa tin nhắn

use knock_knock_project;

DROP PROCEDURE IF EXISTS spMessage_UpdateMessage;

delimiter //

CREATE PROCEDURE spMessage_UpdateMessage(in id_room int, in id_person int, in messagecontent varchar(128))

begin

update message set message.messagecontent = messagecontent where message.id_room=id_room and message.id_person=id_person;

end//

delimiter ;

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------- Delete

-- Xóa tin nhắn theo id_room và id_person

use knock_knock_project;

DROP PROCEDURE IF EXISTS spMessage_DeleteMessage;

delimiter //

CREATE PROCEDURE spMessage_DeleteMessage(in id_room int,in id_person int)

begin

delete from message where message.id_room=id_room and message.id_person=id_person ;

end//

delimiter ;