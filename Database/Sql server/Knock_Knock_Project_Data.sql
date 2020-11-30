USE Knock_Knock_Project;

-- Lưu giá trị khởi tạo cho CSDL

-- ----------------------------------------------------------------------------------------------
-- --------------------------------------- Giá trị cho bảng Person
-- ----------------------------------------------------------------------------------------------

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('admin', '1234', 'Administrator', '1', '0000000000', '2020-10-04', 'anh-avatar-supreme-dep-lam-dai-dien-facebook.jpg');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('user1', '1234', 'User1', '1', '0000000001', '2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('user2', '1234', 'User2', '0', '0000000002', '2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('user3', '1234', 'User3', '0', '0000000003', '2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('user4', '1234', 'User4', '0', '0000000004', '2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('user5', '1234', 'User5', '0', '0000000005', '2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('user6', '1234', 'User6', '0', '0000000006', '2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('user7', '1234', 'User7', '0', '0000000007', '2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('user8', '1234', 'User8', '0', '0000000008', '2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('user9', '1234', 'User9', '0', '0000000009', '2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('user10', '1234', 'User10 WTF', '0', '0000000010', '2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('legiao', '1234', 'Lê Giao', '0', '0987072222', '2000-11-05', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('firefly', '1234', 'Thạch Chí Tâm', '1', '0907070422', '2000-07-09', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('chaubathong', '1234', 'Minh Thông', '1', '0987654321', '1999-06-12', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('vovy', '1234', 'Vy Vy', '0', '6868685843', '2000-03-31', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('mathew', '1234', 'Thành Đạt', '1', '1246843333', '2000-08-15', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('minhthu', '1234', 'Minh Thư', '0', '675849922', '2000-11-22', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('thambuoi', '1234', 'Nguyễn Thắm', '0', '3425165786', '2000-08-27', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('congle', '1234', 'Công Công', '1', '0909988767', '2000-02-28', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('ducquynh', '1234', 'Quỳnh Đức', '1', '6733445533', '2000-08-30', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('thotho', '1234', 'Văn Thọ', '1', '8584843828', '2001-02-27', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('nhanduyen', '1234', 'Thành Nhân', '1', '8473625485', '1998-07-13', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('nguyenngoc', '1234', 'Khả Ái', '0', '9685473621', '2005-09-19', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('khoinguyen', '1234', 'Lê Khôi', '1', '4678362522', '2000-10-23', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('minhanh', '1234', 'Minh Ánh', '0', '0908070533', '2000-04-12', 'default_avatar.png');

INSERT INTO Person(username, [password], [name], gender, phonenumber, dateofbirth, avatar)
VALUES('nguyetnga', '1234', 'Nguyệt Nga', '0', '573722222', '2000-01-15', 'default_avatar.png');


SELECT * FROM Person;

-- ----------------------------------------------------------------------------------------------
-- --------------------------------------- Giá trị cho bảng phòng
-- ----------------------------------------------------------------------------------------------

INSERT INTO Room([name])
VALUES('Phòng 1');

INSERT INTO Room([name])
VALUES('Phòng 2');

INSERT INTO Room([name])
VALUES('Phòng 3');

INSERT INTO Room([name])
VALUES('Phòng 4');

INSERT INTO Room([name])
VALUES('Phòng 5');

INSERT INTO Room([name])
VALUES('Phòng 6');

INSERT INTO Room([name])
VALUES('Phòng 7');

INSERT INTO Room([name])
VALUES('Phòng 8');

INSERT INTO Room([name])
VALUES('Phòng 9');

INSERT INTO Room([name])
VALUES('Phòng 10');

UPDATE Room SET avatar = 'sources/default/avatars/default_room_avatar.jpg' 
WHERE id < 11;

SELECT * FROM Room;

-- ----------------------------------------------------------------------------------------------
-- --------------------------------------- Giá trị cho thành viên trong phòng
-- ----------------------------------------------------------------------------------------------


-- Phòng 3 có user 1, 2, 3, 4, 5
INSERT INTO Person_Room(id_room, id_person)
VALUES('1', '1');
INSERT INTO Person_Room(id_room, id_person)
VALUES('1', '2');
INSERT INTO Person_Room(id_room, id_person)
VALUES('1', '3');
INSERT INTO Person_Room(id_room, id_person)
VALUES('1', '4');
INSERT INTO Person_Room(id_room, id_person)
VALUES('1', '5');


-- Phòng 2 có user 2, 3, 4, 5, 6
INSERT INTO Person_Room(id_room, id_person)
VALUES('2', '2');
INSERT INTO Person_Room(id_room, id_person)
VALUES('2', '3');
INSERT INTO Person_Room(id_room, id_person)
VALUES('2', '4');
INSERT INTO Person_Room(id_room, id_person)
VALUES('2', '5');
INSERT INTO Person_Room(id_room, id_person)
VALUES('2', '6');

-- Phòng 3 có user 1, 3, 5, 7, 9
INSERT INTO Person_Room(id_room, id_person)
VALUES('3', '1');
INSERT INTO Person_Room(id_room, id_person)
VALUES('3', '3');
INSERT INTO Person_Room(id_room, id_person)
VALUES('3', '5');
INSERT INTO Person_Room(id_room, id_person)
VALUES('3', '7');
INSERT INTO Person_Room(id_room, id_person)
VALUES('3', '9');


-- Phòng 4 có user 2, 4, 6, 8, 10
INSERT INTO Person_Room(id_room, id_person)
VALUES('4', '2');
INSERT INTO Person_Room(id_room, id_person)
VALUES('4', '4');
INSERT INTO Person_Room(id_room, id_person)
VALUES('4', '6');
INSERT INTO Person_Room(id_room, id_person)
VALUES('4', '8');
INSERT INTO Person_Room(id_room, id_person)
VALUES('4', '10');

-- Phòng 5 có user 11, 12, 13, 14, 15, 16
INSERT INTO Person_Room(id_room, id_person)
VALUES('5', '11');
INSERT INTO Person_Room(id_room, id_person)
VALUES('5', '12');
INSERT INTO Person_Room(id_room, id_person)
VALUES('5', '13');
INSERT INTO Person_Room(id_room, id_person)
VALUES('5', '14');
INSERT INTO Person_Room(id_room, id_person)
VALUES('5', '15');
INSERT INTO Person_Room(id_room, id_person)
VALUES('5', '16');

SELECT * FROM Person_Room;

-- ----------------------------------------------------------------------------------------------
-- --------------------------------------- Giá trị cho bảng bạn bè
-- ----------------------------------------------------------------------------------------------


-- User 1 là bạn của User 2  => User 2 là bạn của User 1
INSERT INTO Friend(id_person, id_friend)
VALUES('1', '2');
INSERT INTO Friend(id_person, id_friend)
VALUES('2', '1');

-- User 2 là bạn của User 3
INSERT INTO Friend(id_person, id_friend)
VALUES('2', '3');
INSERT INTO Friend(id_person, id_friend)
VALUES('3', '2');

-- User 2 là bạn của User 4
INSERT INTO Friend(id_person, id_friend)
VALUES('2', '4');
INSERT INTO Friend(id_person, id_friend)
VALUES('4', '2');

-- User 2 là bạn của User 5
INSERT INTO Friend(id_person, id_friend)
VALUES('2', '5');
INSERT INTO Friend(id_person, id_friend)
VALUES('5', '2');

-- User 2 là bạn của User 6
INSERT INTO Friend(id_person, id_friend)
VALUES('2', '6');
INSERT INTO Friend(id_person, id_friend)
VALUES('6', '2');

-- User 11 là bạn của User 12
INSERT INTO Friend(id_person, id_friend)
VALUES('11', '12');
INSERT INTO Friend(id_person, id_friend)
VALUES('12', '11');

-- User 11 là bạn của User 13
INSERT INTO Friend(id_person, id_friend)
VALUES('11', '13');
INSERT INTO Friend(id_person, id_friend)
VALUES('13', '11');

-- User 11 là bạn của User 14
INSERT INTO Friend(id_person, id_friend)
VALUES('11', '14');
INSERT INTO Friend(id_person, id_friend)
VALUES('14', '11');

-- User 11 là bạn của User 15
INSERT INTO Friend(id_person, id_friend)
VALUES('11', '15');
INSERT INTO Friend(id_person, id_friend)
VALUES('15', '11');

SELECT * FROM Friend;


-- ----------------------------------------------------------------------------------------------
-- --------------------------------------- Giá trị cho bảng tin nhắn
-- ----------------------------------------------------------------------------------------------

-- Phòng 1

-- User 1 gửi hello vào phòng 1
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('1', '1', 'Hello');
-- User 2 gửi hallo vào phòng 1
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('1', '2', 'Hallo');
-- User 2 gửi hi vào phòng 1
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('1', '3', 'Hi');

-- Phòng 2
-- User 2 gửi ciao vào phòng 2
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('2', '2', 'Ciao');
-- User 3 gửi Osu vào phòng 2
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('2', '3', 'Osu');

-- Phòng 3
-- User 1 gửi xin chào vào phòng 3
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('3', '2', 'Xin chào');
-- User 3 gửi chào bạn vào phòng 3
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('3', '3', 'Chào bạn');

SELECT * FROM Message;