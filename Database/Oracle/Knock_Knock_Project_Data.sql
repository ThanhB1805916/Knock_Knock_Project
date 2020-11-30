
-- Lýu giá tr? kh?i t?o cho CSDL

-- ----------------------------------------------------------------------------------------------
-- --------------------------------------- Giá tr? cho b?ng Person
-- ----------------------------------------------------------------------------------------------
INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('admin', '1234', 'Administrator', '1', '0000000000', DATE'2020-10-04', 'anh-avatar-supreme-dep-lam-dai-dien-facebook.jpg');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('user1', '1234', 'User1', '1', '0000000001', DATE'2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('user2', '1234', 'User2', '0', '0000000002', DATE'2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('user3', '1234', 'User3', '0', '0000000003', DATE'2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('user4', '1234', 'User4', '0', '0000000004', DATE'2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('user5', '1234', 'User5', '0', '0000000005', DATE'2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('user6', '1234', 'User6', '0', '0000000006', DATE'2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('user7', '1234', 'User7', '0', '0000000007', DATE'2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('user8', '1234', 'User8', '0', '0000000008', DATE'2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('user9', '1234', 'User9', '0', '0000000009', DATE'2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('user10', '1234', 'User10 WTF', '0', '0000000010', DATE'2020-10-04', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('legiao', '1234', 'Lê Giao', '0', '0987072222', DATE'2000-11-05', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('firefly', '1234', 'Th?ch Chí Tâm', '1', '0907070422', DATE'2000-07-09', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('chaubathong', '1234', 'Minh Thông', '1', '0987654321', DATE'1999-06-12', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('vovy', '1234', 'Vy Vy', '0', '0868685843', DATE'2000-03-31', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('mathew', '1234', 'Thành Ð?t', '1', '0246843333', DATE'2000-08-15', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('minhthu', '1234', 'Minh Thý', '0', '0758499229', DATE'2000-11-22', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('thambuoi', '1234', 'Nguy?n Th?m', '0', '0425165786', DATE'2000-08-27', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('congle', '1234', 'Công Công', '1', '0909988768', DATE'2000-02-28', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('ducquynh', '1234', 'Qu?nh Ð?c', '1', '0733445533', DATE'2000-08-30', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('thotho', '1234', 'Vãn Th?', '1', '0584843828', DATE'2001-02-27', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('nhanduyen', '1234', 'Thành Nhân', '1', '0473625485', DATE'1998-07-13', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('nguyenngoc', '1234', 'Kh? Ái', '0', '0685473621', DATE'2005-09-19', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('khoinguyen', '1234', 'Lê Khôi', '1', '0678362522', DATE'2000-10-23', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('minhanh', '1234', 'Minh Ánh', '0', '0908070533', DATE'2000-04-12', 'default_avatar.png');

INSERT INTO Person(username, "password", "name", gender, phonenumber, dateofbirth, avatar)
VALUES('nguyetnga', '1234', 'Nguy?t Nga', '0', '0737222222', DATE'2000-01-15', 'default_avatar.png');


SELECT * FROM Person;

-- ----------------------------------------------------------------------------------------------
-- --------------------------------------- Giá tr? cho b?ng ph?ng
-- ----------------------------------------------------------------------------------------------

INSERT INTO Room("name", datecreate)
VALUES('Phong 1', TIMESTAMP'2020-11-09 09:00:00');

INSERT INTO Room("name")
VALUES('Ph?ng 2');

INSERT INTO Room("name")
VALUES('Ph?ng 3');

INSERT INTO Room("name")
VALUES('Ph?ng 4');

INSERT INTO Room("name")
VALUES('Ph?ng 5');

INSERT INTO Room("name")
VALUES('Ph?ng 6');

INSERT INTO Room("name")
VALUES('Ph?ng 7');

INSERT INTO Room("name")
VALUES('Ph?ng 8');

INSERT INTO Room("name")
VALUES('Ph?ng 9');

INSERT INTO Room("name")
VALUES('Ph?ng 10');

UPDATE Room SET avatar = 'default_room_avatar.jpg' 
WHERE "id" < 11;

SELECT * FROM Room;

-- ----------------------------------------------------------------------------------------------
-- --------------------------------------- Giá tr? cho thành viên trong ph?ng
-- ----------------------------------------------------------------------------------------------

-- Ph?ng 1 có user 1, 2
INSERT INTO Person_Room(id_room, id_person)
VALUES('1', '1');
INSERT INTO Person_Room(id_room, id_person)
VALUES('1', '2');

-- Ph?ng 2 có user 2, 3, 4, 5, 6
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

-- Ph?ng 3 có user 3, 5, 7, 9
INSERT INTO Person_Room(id_room, id_person)
VALUES('3', '3');
INSERT INTO Person_Room(id_room, id_person)
VALUES('3', '5');
INSERT INTO Person_Room(id_room, id_person)
VALUES('3', '7');
INSERT INTO Person_Room(id_room, id_person)
VALUES('3', '9');


-- Ph?ng 4 có user 2, 4, 6, 8, 10
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

-- Ph?ng 5 có user 11, 12, 13, 14, 15, 16
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
-- --------------------------------------- Giá tr? cho b?ng b?n bè
-- ----------------------------------------------------------------------------------------------


-- User 1 là b?n c?a User 2  => User 2 là b?n c?a User 1
INSERT INTO Friend(id_person, id_friend, isFriend)
VALUES('1', '2', '1');
INSERT INTO Friend(id_person, id_friend, isFriend)
VALUES('2', '1', '1');

-- User 2 là b?n c?a User 3
INSERT INTO Friend(id_person, id_friend, isFriend)
VALUES('2', '3', '1');
INSERT INTO Friend(id_person, id_friend, isFriend)
VALUES('3', '2', '1');

-- User 2 g?i l?i m?i k?t b?n t?i user 4
INSERT INTO Friend(id_person, id_friend)
VALUES('2', '4');
INSERT INTO Friend(id_person, id_friend)
VALUES('4', '2');

-- User 2 g?i l?i m?i k?t b?n t?i user 5
INSERT INTO Friend(id_person, id_friend)
VALUES('2', '5');
INSERT INTO Friend(id_person, id_friend)
VALUES('5', '2');

-- User 2 là b?n c?a User 6
INSERT INTO Friend(id_person, id_friend, isFriend)
VALUES('2', '6', '1');
INSERT INTO Friend(id_person, id_friend, isFriend)
VALUES('6', '2', '1');

-- User 11 g?i l?i m?i k?t b?n t?i user 12
INSERT INTO Friend(id_person, id_friend)
VALUES('11', '12');
INSERT INTO Friend(id_person, id_friend)
VALUES('12', '11');

-- User 11 là b?n c?a User 13
INSERT INTO Friend(id_person, id_friend)
VALUES('11', '13');
INSERT INTO Friend(id_person, id_friend)
VALUES('13', '11');

-- User 11 là b?n c?a User 14
INSERT INTO Friend(id_person, id_friend, isFriend)
VALUES('11', '14', '1');
INSERT INTO Friend(id_person, id_friend, isFriend)
VALUES('14', '11', '1');

-- User 11 là b?n c?a User 15
INSERT INTO Friend(id_person, id_friend, isFriend)
VALUES('11', '15', '1');
INSERT INTO Friend(id_person, id_friend, isFriend)
VALUES('15', '11', '1');

-- SELECT * FROM Friend;


-- ----------------------------------------------------------------------------------------------
-- --------------------------------------- Giá tr? cho b?ng tin nh?n
-- ----------------------------------------------------------------------------------------------

-- Ph?ng 1

-- User 1 g?i hello vào ph?ng 1
INSERT INTO Message(id_room, id_person, messagecontent, sendtime)
VALUES('1', '1', 'Hello', TIMESTAMP'2020-09-11 09:01:00');
-- User 2 g?i hallo vào ph?ng 1
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('1', '2', 'Hallo');
-- User 2 g?i hi vào ph?ng 1
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('1', '3', 'Hi');

-- Ph?ng 2
-- User 2 g?i ciao vào ph?ng 2
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('2', '2', 'Ciao');
-- User 3 g?i Osu vào ph?ng 2
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('2', '3', 'Osu');

-- Ph?ng 3
-- User 1 g?i xin chào vào ph?ng 3
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('3', '2', 'Xin chào');
-- User 3 g?i chào b?n vào ph?ng 3
INSERT INTO Message(id_room, id_person, messagecontent)
VALUES('3', '3', 'Chào b?n');

SELECT * FROM Message;